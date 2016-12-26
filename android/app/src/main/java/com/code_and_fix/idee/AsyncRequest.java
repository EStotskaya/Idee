package com.code_and_fix.idee;


import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;


import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Mary on 22.12.2016.
 */

public class AsyncRequest extends AsyncTask {

    private String mURLString, mResponseData = null ,mMethod = null;
    private String mPostData;
    private String mPostDataString = null;
    private boolean mIsPost = false;


    public AsyncRequest(String url, String data) {
        mURLString = url;
        mPostData = data;

        execute();
    }
    public AsyncRequest(String url, String data, String method) {
        mURLString = url;
        mPostData = data;
        mMethod = method;
        execute();
    }

    public AsyncRequest(String url, String data, String method, Boolean isPost) {
        mURLString = url;
        mPostDataString = data;
        mIsPost = isPost;
        mMethod = method;
        execute();
    }

    @Override
    protected Object doInBackground(Object[] params) {

        URL url  = null;
        try {
            url = new URL(mURLString);
            String response = "";
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setReadTimeout(15000);
            connection.setConnectTimeout(15000);


            if (mPostData != null || mIsPost) {
                connection.setRequestMethod("POST");
                if (mPostDataString != null && !mPostDataString.isEmpty()) {
                    connection.setRequestProperty("Content-Type", "application/json");
                }
                connection.setDoOutput(true);
                OutputStream os = connection.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                if (mPostData != null) {
                    bw.write(mPostData);
                } else if (mPostDataString != null && !mPostDataString.isEmpty()) {
                    bw.write(mPostDataString);
                }
                bw.flush();
                bw.close();
                os.close();

            }
            else {
                try {
                    connection.setRequestMethod("GET");


                } catch (ProtocolException e) {
                    e.printStackTrace();
                }
            }


            int responseCode = connection.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;

                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line=br.readLine()) != null) {
                    response+=line;

                }
                br.close();
                mResponseData = response;
                System.out.println("Got json");
            } else {
                String error;
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((error=br.readLine()) != null) {
                    response+=error;
                }
                br.close();
                mResponseData = null;

            }

        }
        catch (IOException e) {

            if(e.getMessage()==null||e.getMessage().contains("to resolve host")){

            }else {

            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        if (mResponseData != null) {
            System.out.println(mResponseData);
        }


    }
}
