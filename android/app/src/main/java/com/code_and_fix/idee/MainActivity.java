package com.code_and_fix.idee;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.ButterKnife;
import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sPref;

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
        intent.addFlags(Intent.FLAG_ACTIVITY_TASK_ON_HOME);
        startActivity(intent);
    }

    @OnClick(R.id.signToApp) public void signToApp(Button button)
    {
        Intent intent = new Intent(this, AppActivity.class);
        intent.putExtra(AppActivity.LOGIN_INFO, editLogin.getText().toString());
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        startActivity(intent);
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


        {
            sPref=getSharedPreferences("accInfo", MODE_PRIVATE);
            editLogin.setText(sPref.getString("Saved login", ""));
            editPass.setText(sPref.getString("Saved pass", ""));

        }
    }


}
