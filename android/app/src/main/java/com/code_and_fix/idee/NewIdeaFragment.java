package com.code_and_fix.idee;


import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.opengl.Visibility;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.google.gson.Gson;




/**
 * A simple {@link Fragment} subclass.
 */
public class NewIdeaFragment extends Fragment {


    @Bind(R.id.relLay) RelativeLayout relLay;
    @Bind(R.id.submit) Button submit;
    @Bind(R.id.ideaBody) EditText ideaBody;
    @Bind(R.id.addTag) Button addTag;

    private boolean tagWasClicked;

    @OnClick(R.id.submit) public void jsonClick(Button button)
    {
        submit();
    }

    @OnClick(R.id.addTag) public void addTag(Button butt)
    {
        addTag();
        butt.setVisibility(View.GONE);
        tagWasClicked = true;
    }


    public NewIdeaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //change later


        View view = inflater.inflate(R.layout.fragment_new_idea, container, false);
        ButterKnife.bind(this, view);



        try
        {
            SQLiteOpenHelper baseHelper = new BaseHelper(getActivity());
            SQLiteDatabase db = baseHelper.getReadableDatabase();
            Cursor cursor = db.query("saved_ideas", new String[]{"idea", "tag"}, "name = ?", new String[]{AppActivity.LOGIN_INFO}, null, null, null);
            if(cursor.moveToLast())
            {
                ideaBody.setText(cursor.getString(0));

                try{
                    if(getView().findViewById(R.id.tag) != null)
                    {
                        EditText tag = (EditText) getView().findViewById(R.id.tag);
                        tag.setText(cursor.getString(1));
                    }
                }catch(NullPointerException e)
                {
                    System.out.println(e);
                }
                db.delete("saved_ideas", null, null);
            }
            cursor.close();
            db.close();
        }
        catch(SQLiteException e)
        {
            Toast.makeText(getActivity(), "Can't access DB", Toast.LENGTH_LONG).show();
        }
        return view;
    }

    @Override
    public void onPause()
    {
        super.onPause();
        if(!isOnline())
        {
            try
            {
                String s = "Saved idea: ";
                SQLiteOpenHelper baseHelper = new BaseHelper(getActivity());
                SQLiteDatabase db = baseHelper.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put("name", AppActivity.LOGIN_INFO);
                cv.put("idea", ideaBody.getText().toString());
                s += " ' " + ideaBody.getText().toString() + " ' ";
                try
                {
                    if(getView().findViewById(R.id.tag) != null)
                    {
                        EditText tag = (EditText)getView().findViewById(R.id.tag);
                        cv.put("tag", tag.getText().toString());
                        s += ", tag: ' " + tag.getText().toString() + " ' ";
                    }
                }catch(NullPointerException e)
                {
                    System.out.println(e);
                }
                db.insert("saved_ideas", null, cv);

                Toast.makeText(getActivity(), "No internet connection \n" + s, Toast.LENGTH_LONG).show();
            }
            catch(SQLiteException e)
            {
                Toast.makeText(getActivity(), "Can't write to DB", Toast.LENGTH_LONG).show();
            }
        }


        Bundle b = new Bundle();
        onSaveInstanceState(b);
    }


    private JSONObject submit()
    {
        EditText tag = (EditText) getView().findViewById(R.id.tag);


        myJSONClass myJson = new myJSONClass(AppActivity.LOGIN_INFO, ideaBody.getText().toString(), tag.getText().toString());


        Gson gson = new Gson();
        String my = gson.toJson(myJson);

        JSONObject json = new JSONObject();
        try{
            json = new JSONObject(my);
            Toast.makeText(this.getActivity(), json.toString(), Toast.LENGTH_LONG).show();
        }catch(JSONException e)
        {
            Toast.makeText(this.getActivity(), "Something went wrong", Toast.LENGTH_LONG).show();
        }


        return json;
    }

    private class myJSONClass
    {
        private String name = "";
        private String idea = "";
        private String tag = "";

        private myJSONClass(String name, String idea, String tag)
        {
            this.name = name;
            this.idea = idea;
            this.tag = tag;
        }
    }

    private boolean isOnline()
    {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nf = cm.getActiveNetworkInfo();
        return nf != null && nf.isConnectedOrConnecting();
    }

    private void addTag()
    {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.BELOW, R.id.addTag);

        EditText tag = new EditText(this.getActivity());
        tag.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        tag.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        tag.setMaxLines(1);
        tag.setHint("#add_your_tag");
        tag.setId(R.id.tag);

        relLay.addView(tag, params);

    }

    @Override
    public void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("tag", tagWasClicked);
    }



}
