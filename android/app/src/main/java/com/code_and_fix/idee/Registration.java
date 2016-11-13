package com.code_and_fix.idee;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Registration extends AppCompatActivity {

    @Bind(R.id.loginEdit) EditText login;
    @Bind(R.id.passEdit) EditText pass;
    @Bind(R.id.backButt) Button backButt;
    @Bind(R.id.register) Button regButt;
    SharedPreferences sp;

    @OnClick(R.id.backButt) public void back(Button butt)
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
                    edit.commit();
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
            edit.commit();
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();

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

        sp=getSharedPreferences("regInfo", MODE_PRIVATE);
        login.setText(sp.getString("Saved login", ""));
        pass.setText(sp.getString("Saved pass", ""));
    }


    //better add regEx
    String check()
    {
        String mess = "";

        if(!((login.getText().toString() != null)&&(login.getText().toString().length()>3)&&(login.getText().toString().length()<11)))
            mess += "Invalid login\n";

        if(!((pass.getText().toString()!=null)&&(pass.getText().toString().length()>3)&&(pass.getText().toString().length()<11)))
            mess += "Invalid password";

        return mess;
    }

}
