package com.example.codezero.mysqldb;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class SkillDevelopment extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner skillsSP;
    String skills;
    TextView tcTV,tpTV,regTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill_development);

        getSupportActionBar().setTitle("Skill Development");

        skillsSP = (Spinner) findViewById(R.id.skillListSP);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.skills, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        skillsSP.setAdapter(adapter);
        skillsSP.setOnItemSelectedListener(this);

        tcTV=(TextView)findViewById(R.id.tcTV);
        tpTV=(TextView)findViewById(R.id.tpTV);
        regTV=(TextView)findViewById(R.id.regTV);

        tcTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri saiUri = Uri.parse("http://pmkvyofficial.org/Training-Centre.aspx");

                // Create a new intent to view the Training Centres URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, saiUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });

        regTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri saiUri = Uri.parse("https://www.startupindia.gov.in/startup-recognition.php");

                // Create a new intent to view the Startup Registraion Page URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, saiUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });

        tpTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri saiUri = Uri.parse("http://pmkvyofficial.org/BecomeaTrainingPartner.aspx");

                // Create a new intent to view the Training Partners URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, saiUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Spinner spinner = (Spinner) adapterView;
        skills = adapterView.getItemAtPosition(i).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    //On click method for Search Buyers Button.
    public void searchSkills(View view) {

        // Search for Buyers nearby
        Uri gmmIntentUri = Uri.parse("geo:0,0?q="+skills+"+buyers");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);

    }

}
