package com.code_and_fix.idee;


import android.app.ActionBar;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class NewIdeaFragment extends Fragment {


    @Bind(R.id.relLay) RelativeLayout relLay;
    @Bind(R.id.submit) Button submit;
    @Bind(R.id.ideaBody) EditText ideaBody;
    @Bind(R.id.addTag) Button addTag;

    @OnClick(R.id.submit) public void jsonClick(Button button)
    {
        submit();
    }

    @OnClick(R.id.addTag) public void addTag(Button butt)
    {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.BELOW, R.id.addTag);

        EditText tag = new EditText(this.getActivity());
        tag.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        tag.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        tag.setMaxLines(1);

        relLay.addView(tag, params);



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
        return view;
    }

    private JSONObject submit()
    {
        JSONObject json = new JSONObject();
        try{
            json = new JSONObject("{\"name\" : " + AppActivity.LOGIN_INFO + " , \"idea\" : " + ideaBody.getText().toString() + " }");
            Toast.makeText(getActivity(), json.toString(), Toast.LENGTH_LONG).show();
        }catch(JSONException e)
        {
            System.out.print("Something went wrong");
        }


        return json;
    }


}
