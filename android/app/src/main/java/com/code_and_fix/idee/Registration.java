package com.code_and_fix.idee;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ButterKnife.bind(this);
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
