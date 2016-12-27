package com.code_and_fix.idee;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.ButterKnife;
import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sPref;
    Fonts fonts = new Fonts(this);

    @Bind(R.id.editLogin)
    EditText editLogin;
    @Bind(R.id.editPass)
    EditText editPass;
    @Bind(R.id.signIn)
    Button signIn;
    @Bind(R.id.signUp)
    Button signUp;
    @Bind(R.id.anonymous)
    Button anonymous;
    @Bind(R.id.back)
    Button backButt;
    @Bind(R.id.signToApp)
    Button signToApp;
    @Bind(R.id.text)
    TextView appName;

    @OnClick(R.id.signIn)
    void showEditText(Button butt) {
        editLogin.setVisibility(View.VISIBLE);
        editPass.setVisibility(View.VISIBLE);
        backButt.setVisibility(View.VISIBLE);
        signToApp.setVisibility(View.VISIBLE);
        signIn.setVisibility(View.GONE);
        signUp.setVisibility(View.GONE);
        anonymous.setVisibility(View.GONE);
    }

    @OnClick(R.id.back)
    void backToButtons(Button butt) {
        editLogin.setVisibility(View.GONE);
        editPass.setVisibility(View.GONE);
        backButt.setVisibility(View.GONE);
        signToApp.setVisibility(View.GONE);
        signIn.setVisibility(View.VISIBLE);
        signUp.setVisibility(View.VISIBLE);
        anonymous.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.signUp)
    public void registration(Button butt) {
        Intent intent = new Intent(this, Registration.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

    @OnClick(R.id.signToApp) public void signToApp(Button button)
    {
        if(editLogin.getText().toString().length()>0 && editPass.getText().toString().length()>0) {
            String url = "http://darkside2016.herokuapp.com:80/login?name=" + editLogin.getText().toString() + "&password=" + editPass.getText().toString();
            String login = editLogin.getText().toString();
            new myAsyncTask().execute(url, login);
        }
        else
        {
            Toast.makeText(this, "Something is wrong", Toast.LENGTH_LONG).show();
        }

    }

    @OnClick(R.id.anonymous)
    void startApp(Button butt)
    {
        Intent intent = new Intent(this, AppActivity.class);
        intent.putExtra(AppActivity.LOGIN_INFO, "Anonymous");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ButterKnife.bind(this);
        appName.setTypeface(fonts.lobster());
        signIn.setTypeface(fonts.caviarBold());
        signUp.setTypeface(fonts.caviarBold());
        anonymous.setTypeface(fonts.caviarBold());
        backButt.setTypeface(fonts.caviarBold());
        signToApp.setTypeface(fonts.caviarBold());
        editLogin.setTypeface(fonts.caviarNorm());
        editPass.setTypeface(fonts.caviarNorm());


        {
            sPref=getSharedPreferences("accInfo", MODE_PRIVATE);
            editLogin.setText(sPref.getString("Saved login", ""));
            editPass.setText(sPref.getString("Saved pass", ""));

        }


    }

    public class myAsyncTask extends AsyncTask<String, Void, String>
    {
        HttpURLConnection http;
        BufferedReader buffer;

        URL url;

        @Override
        protected void onPreExecute()
        {
            Log.d("Begin","AsyncTask");
            Toast.makeText(MainActivity.this, "Try to connect...", Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(String...params)
        {

            try {
                url = new URL(params[0]);

                http= (HttpURLConnection)url.openConnection();
                http.setRequestMethod("GET");
                http.setRequestProperty("User-Agent", ConnectParams.USER_AGENT);

                http.connect();


                int responseCode = http.getResponseCode();

                if(responseCode < 400)
                {
                    StringBuffer sbuffer = new StringBuffer();
                    buffer = new BufferedReader(new InputStreamReader(http.getInputStream()));
                    String line = "";

                    while ((line = buffer.readLine()) != null) {

                        sbuffer.append(line);
                    }
                    buffer.close();

                    String token = sbuffer.toString();


                    {

                        Intent intent = new Intent(MainActivity.this, AppActivity.class);
                        intent.putExtra(AppActivity.LOGIN_INFO, params[1]);
                        intent.putExtra("token", token);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        finish();
                        startActivity(intent);
                    }

                    return sbuffer.toString();

                }

            }catch(Exception e)
            {
                Log.e("Bad URL", "Check url");
            }

            return null;
        }

        @Override
        protected void onPostExecute(String json)
        {
            super.onPostExecute(json);



        }


    }




}
