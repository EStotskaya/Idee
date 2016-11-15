package com.code_and_fix.idee;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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



    @Bind(R.id.submit) Button submit;
    @Bind(R.id.ideaBody) EditText ideaBody;
    @Bind(R.id.addTag) Button addTag;

    @OnClick(R.id.submit) public void jsonClick(Button button)
    {
        submit();
    }


    public NewIdeaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //change later
        ButterKnife.bind(this.getActivity());
        return inflater.inflate(R.layout.fragment_new_idea, container, false);
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
