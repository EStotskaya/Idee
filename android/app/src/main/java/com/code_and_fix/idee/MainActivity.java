package com.code_and_fix.idee;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import butterknife.ButterKnife;
import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sPref;

    @Bind(R.id.editLogin) EditText editLogin;
    @Bind(R.id.editPass) EditText editPass;
    @Bind(R.id.signIn) Button signIn;
    @Bind(R.id.signUp) Button signUp;
    @Bind(R.id.anonymous) Button anonymous;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ButterKnife.bind(this);


        if(savedInstanceState!=null)
        {
            sPref=getSharedPreferences("accInfo", MODE_PRIVATE);
            editLogin.setText(sPref.getString("Saved login", ""));
            editPass.setText(sPref.getString("Saved pass", ""));

        }
    }


}
