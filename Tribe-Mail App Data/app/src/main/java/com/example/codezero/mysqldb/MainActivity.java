package com.example.codezero.mysqldb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText usernameET, passwordET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set the views.
        usernameET = (EditText) findViewById(R.id.usernameEditText);
        passwordET = (EditText) findViewById(R.id.passwordEditText);
    }

    //On Click method for login button.
    public void onLogin(View view){

        String username = usernameET.getText().toString();
        String password = passwordET.getText().toString();
        String type = "login";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type,username,password);
        //finish();
    }

    //On Click method for register button.
    public void onRegister(View view){

        Intent intent =new Intent(this,RegisterActivity.class);
        startActivity(intent);
        finish();

    }
}
