package com.code_and_fix.idee;


import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyCabinetFragment extends Fragment {

    @Bind(R.id.name) TextView name;
    @Bind(R.id.profilePic) ImageView profilePic;
    @Bind(R.id.changePic) Button changePic;
    @Bind(R.id.idea) TextView ideaTitle;
    @Bind(R.id.title) TextView cabTitle;
    Fonts fonts;

    SharedPreferences spref;

    final int PICK_PIC = 1;

    @OnClick(R.id.changePic) void picImage(Button butt)
    {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_PIC);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        switch (requestCode) {
            case PICK_PIC:
                if (resultCode == RESULT_OK) {
                    try {
                        final Uri imageUri = intent.getData();
                        String imageName = imageUri.toString();
                        spref = getActivity().getSharedPreferences("accInfo", MODE_PRIVATE);
                        SharedPreferences.Editor edit = spref.edit();
                        edit.putString("image", imageName);
                        edit.apply();

                        ImageLoader loader = ImageLoader.getInstance();
                        ImageSize target = new ImageSize(200, 200);
                        loader.displayImage(imageName, profilePic, target);


                    } catch (Exception e) {
                        Toast.makeText(getActivity(), "Can't load image", Toast.LENGTH_LONG).show();
                    }
                }
        }
    }

    public MyCabinetFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_my_cabinet, container, false);
        ButterKnife.bind(this,view);
        fonts = new Fonts(getActivity());
        cabTitle.setTypeface(fonts.caviarBold());
        ideaTitle.setTypeface(fonts.caviarBold());
        changePic.setTypeface(fonts.caviarNorm());
        name.setTypeface(fonts.caviarNorm());

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getActivity()).build();
        ImageLoader.getInstance().init(config);

        spref = getActivity().getSharedPreferences("accInfo", MODE_PRIVATE);
        String imagename = spref.getString("image", "");

        Intent intent = getActivity().getIntent();
        if (intent != null)
        {
            if(intent.getStringExtra(AppActivity.LOGIN_INFO) != null)
            {
                name.setText(intent.getStringExtra(AppActivity.LOGIN_INFO));
            }

        }

        if(imagename.length()>0)
        {
            ImageLoader loader = ImageLoader.getInstance();
            ImageSize target = new ImageSize(200, 200);
            loader.displayImage(imagename, profilePic, target);
        }
        return view;


    }

    public static MyCabinetFragment newInstance(String name) {

        Bundle args = new Bundle();

        MyCabinetFragment fragment = new MyCabinetFragment();
        args.putString("Name", name);
        fragment.setArguments(args);
        return fragment;
    }

    public class myAsyncTask extends AsyncTask<Void, Void, String> {
        HttpURLConnection http;
        BufferedReader buffer;
        String json;
        URL url;

        @Override
        protected void onPreExecute() {
            Log.d("Begin", "AsyncTask");
            Toast.makeText(getActivity(), "Try to connect...", Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(Void... params) {

            try {
                url = new URL(ConnectParams.BASE_URL + ConnectParams.allIdeas);

                http = (HttpURLConnection) url.openConnection();
                http.setRequestMethod("GET");
                http.setRequestProperty("User-Agent", ConnectParams.USER_AGENT);

                http.connect();


                int responseCode = http.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
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

            } catch (Exception e) {
                Log.e("Bad URL", "Check url");
            }

            return json;
        }

        @Override
        protected void onPostExecute(String json) {
            super.onPostExecute(json);

            Gson gson = new Gson();
            String my = gson.toJson(json);

            JSONObject jSon = new JSONObject();


        }
    }


}
