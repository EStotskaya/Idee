package com.code_and_fix.idee;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllIdeasFragment extends Fragment {

    @Bind(R.id.frame) FrameLayout frame;
    @Bind(R.id.ideasList) ListView ideasList;

    public AllIdeasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //will be changed, when there'll be an adapter and database
        View view = inflater.inflate(R.layout.idea_cell, container, true);

        //new AsyncRequest((ConnectParams.BASE_URL + ConnectParams.URI + ConnectParams.paramsUrl), null, "GET");
        //View view = inflater.inflate(R.layout.fragment_all_ideas, container, false);
        //ButterKnife.bind(this,view);
        new myAsyncTask().execute();

        return inflater.inflate(R.layout.fragment_all_ideas, container, false);
    }

    //+onPostExecute; +change method
    public class myAsyncTask extends AsyncTask<Void, Void, String>
    {
        HttpURLConnection http;
        BufferedReader buffer;
        String json;
        URL url;

        @Override
        protected void onPreExecute()
        {
            Log.d("Begin","AsyncTask");
            Toast.makeText(getActivity(), "Try to connect...", Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(Void...params)
        {

            try {
                url = new URL("http://darkside2016.herokuapp.com:80/"+"idea?id="+1);

                http= (HttpURLConnection)url.openConnection();
                http.setRequestMethod("GET");
                http.setRequestProperty("User-Agent", ConnectParams.USER_AGENT);

                http.connect();


                int responseCode = http.getResponseCode();

                if(responseCode == HttpURLConnection.HTTP_OK)
                {
                    StringBuffer sbuffer = new StringBuffer();
                    buffer = new BufferedReader(new InputStreamReader(http.getInputStream()));
                    String line = "";

                    while ((line = buffer.readLine()) != null) {

                        sbuffer.append(line);
                    }
                    buffer.close();

                    json = sbuffer.toString();
                    Toast.makeText(getActivity(), json, Toast.LENGTH_LONG).show();

                }

            }catch(Exception e)
            {
                Log.e("Bad URL", "Check url");
            }

            return json;
        }

        @Override
        protected void onPostExecute(String json)
        {
            super.onPostExecute(json);

            Gson gson = new Gson();
            String my = gson.toJson(json);
            List<JSONObject> list = new ArrayList<>();

            if(json != null) {
                try {
                    JSONObject jSon = new JSONObject(json);
                    list.add(jSon);

                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Can't load idea", Toast.LENGTH_SHORT).show();
                }


                    //ideasList.setAdapter(new IdeeAdapter(getActivity(), R.layout.idea_cell, list));

            }
            else
            {
                Toast.makeText(getActivity(), "Can't load idea", Toast.LENGTH_SHORT).show();
            }


        }


    }

    public static JSONArray makeArrayFromString(String data) {
        try {
            return new JSONArray(data);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}
