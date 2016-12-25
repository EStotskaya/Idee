package com.code_and_fix.idee;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Registration extends AppCompatActivity {

    @Bind(R.id.loginEdit) EditText login;
    @Bind(R.id.passEdit) EditText pass;
    @Bind(R.id.backButt) Button backButt;
    @Bind(R.id.register) Button regButt;
    @Bind(R.id.regText) TextView regText;
    @Bind(R.id.loginText) TextView loginText;
    @Bind(R.id.passText) TextView passText;
    SharedPreferences sp;
    Fonts fonts;
    Pattern p = Pattern.compile("^[a-zA-Z0-9]{3,11}$");
    String url = "http://darkside2016.herokuapp.com:80/countries?auth=xxx";

    @OnClick(R.id.backButt) public void back(Button butt)
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //View view = ((LayoutInflater)getLayoutInflater()).inflate(R.layout.dialog, null);
        //builder.setView(view);
        builder.setMessage(getString(R.string.regMess));
        builder.setPositiveButton(getString(R.string.da), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (check().length() > 0)
                {
                    Toast.makeText(Registration.this, check(), Toast.LENGTH_LONG).show();
                }

                //change later, must not open start page
                else
                {
                    sp = getSharedPreferences("regInfo", MODE_PRIVATE);
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putString("Saved login", login.getText().toString());
                    edit.putString("Saved pass", pass.getText().toString());
                    edit.apply();
                    Toast.makeText(Registration.this, "Saved", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(Registration.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    finish();
                    startActivity(intent);

                }

            }
        });

        builder.setNegativeButton(getString(R.string.net), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(Registration.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
                startActivity(intent);

            }
        });
        builder.create().show();


    }

    @OnClick(R.id.register) public void register(Button button)
    {
        if(check().length()==0) {
            sp = getSharedPreferences("accInfo", MODE_PRIVATE);
            SharedPreferences.Editor edit = sp.edit();
            edit.putString("Saved login", login.getText().toString());
            edit.putString("Saved pass", pass.getText().toString());
            edit.apply();
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();

            JSONObject json = submit();
            AsyncRequest request = new AsyncRequest(url, json.toString(), "POST");
            Toast.makeText(this, "Registered", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, AppActivity.class);
            intent.putExtra(AppActivity.LOGIN_INFO, login.getText().toString());
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();

        }
        else
        {
            Toast.makeText(this, check(), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ButterKnife.bind(this);
        fonts = new Fonts(this);
        regText.setTypeface(fonts.lobster());
        backButt.setTypeface(fonts.caviarBold());
        regButt.setTypeface(fonts.caviarBold());
        loginText.setTypeface(fonts.caviarBold());
        passText.setTypeface(fonts.caviarBold());
        login.setTypeface(fonts.caviarNorm());
        pass.setTypeface(fonts.caviarNorm());



        sp=getSharedPreferences("regInfo", MODE_PRIVATE);
        login.setText(sp.getString("Saved login", ""));
        pass.setText(sp.getString("Saved pass", ""));
    }



    String check()
    {
        String mess = "";

        if(!((login.getText().toString().length() > 0)&&(p.matcher(login.getText().toString()).matches())))
            mess += "Invalid login\n";

        if(!((pass.getText().toString().length() > 0)&&(pass.getText().toString().length()>3)&&(pass.getText().toString().length()<11)))
            mess += "Invalid password";

        return mess;
    }

    private JSONObject submit()
    {
        Registration.myJSONClass myJson;

        myJson = new Registration.myJSONClass(login.getText().toString(), pass.getText().toString());


        Gson gson = new Gson();
        String my = gson.toJson(myJson);

        JSONObject json = new JSONObject();
        try{
            json = new JSONObject(my);
            Toast.makeText(this, json.toString(), Toast.LENGTH_LONG).show();
        }catch(JSONException e)
        {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }


        return json;
    }

    private class myJSONClass
    {
        private String name = "";
        private String password = "";

        private myJSONClass(String name, String password)
        {
            this.name = name;
            this.password = password;

        }

    }

}
