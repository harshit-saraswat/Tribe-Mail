package com.example.codezero.mysqldb;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SportsAuthority extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner sp_gender, sp_height, sp_bodyType, sp_sports, sp_weight;
    String gender, height, bodytype, sports, weight;

    EditText NameET, AgeET, ContactET, EmailET, QueryET;

    TextView saiLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports_authority);

        getSupportActionBar().setTitle("Sports Authority");

        //Setting SAI Link Text View.
        saiLink=(TextView)findViewById(R.id.saiLink);
        saiLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri saiUri = Uri.parse("http://sportsauthorityofindia.nic.in");

                // Create a new intent to view the SAI URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, saiUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });


        //Setting the Edit Text fields.
        NameET=(EditText)findViewById(R.id.NameET);
        AgeET=(EditText)findViewById(R.id.AgeET);
        ContactET=(EditText)findViewById(R.id.ContactET);
        EmailET=(EditText)findViewById(R.id.EmailET);
        QueryET=(EditText)findViewById(R.id.QueryET);

        //Setting the spinner for Gender list.
        sp_gender = (Spinner) findViewById(R.id.genderSP);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_gender.setAdapter(adapter);
        sp_gender.setOnItemSelectedListener(this);

        //Setting the spinner for Height list.
        sp_height = (Spinner) findViewById(R.id.heightSP);
        adapter = ArrayAdapter.createFromResource(this, R.array.height, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_height.setAdapter(adapter);
        sp_height.setOnItemSelectedListener(this);

        //Setting the spinner for Weight list.
        sp_weight = (Spinner) findViewById(R.id.weightSP);
        adapter = ArrayAdapter.createFromResource(this, R.array.weight, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_weight.setAdapter(adapter);
        sp_weight.setOnItemSelectedListener(this);

        //Setting the spinner for Body Type list.
        sp_bodyType = (Spinner) findViewById(R.id.bodytypeSP);
        adapter = ArrayAdapter.createFromResource(this, R.array.bodytype, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_bodyType.setAdapter(adapter);
        sp_bodyType.setOnItemSelectedListener(this);

        //Setting the spinner for Sports list.
        sp_sports = (Spinner) findViewById(R.id.sportsSP);
        adapter = ArrayAdapter.createFromResource(this, R.array.sports, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_sports.setAdapter(adapter);
        sp_sports.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Spinner spinner = (Spinner) adapterView;

        switch (spinner.getId()) {
            case R.id.genderSP:
                gender = adapterView.getItemAtPosition(i).toString();
                break;
            case R.id.heightSP:
                height = adapterView.getItemAtPosition(i).toString();
                break;
            case R.id.weightSP:
                weight = adapterView.getItemAtPosition(i).toString();
                break;
            case R.id.bodytypeSP:
                bodytype = adapterView.getItemAtPosition(i).toString();
                break;
            case R.id.sportsSP:
                sports = adapterView.getItemAtPosition(i).toString();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    //On click method for searching SAI centres
    public void onSearchSAI(View view){

        // Search for hospitals nearby
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=sports+authority+of+india");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    //On click method for search sports button.
    public void onSearchSports(View view){
        String name= NameET.getText().toString();
        String age= AgeET.getText().toString();
        String contact= ContactET.getText().toString();
        String email= EmailET.getText().toString();
        String query= QueryET.getText().toString();

        String data ="Name: "+name;
        data+="\nAge: "+age;
        data+="\nGender: "+gender;
        data+="\nHeight: "+height;
        data+="\nWeight: "+weight;
        data+="\nBody Type: "+bodytype;
        data+="\nSports Interested In: "+sports;
        data+="\nContact: "+contact;
        data+="\nEmail: "+email;
        data+="\nQuery: "+query;

        if(name.length()==0||age.length()==0){
            Toast.makeText(this, "One or more fields maybe empty.", Toast.LENGTH_SHORT).show();
        }
        else if (contact.length() > 10||contact.length()<10) {
            Toast.makeText(this, "Contact No. should be equal to 10 digits", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Your information will be sent via an e-mail client to the respective authority.", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setData(Uri.parse("mailto:"));
            intent.setType("*/*");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Contacting for a query.");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "saiadmnjns1982@gmail.com" });
            intent.putExtra(Intent.EXTRA_TEXT, data);
            startActivity(Intent.createChooser(intent, "Send Mail"));
        }
    }
}
