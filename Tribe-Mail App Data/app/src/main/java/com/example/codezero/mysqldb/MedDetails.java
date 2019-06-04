package com.example.codezero.mysqldb;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MedDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView schemeNameTV,schemeDetailTV,youtubeLinkTV,doctorLinkTV;
    String name,details,vid;
    Spinner vidLang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_details);

        getSupportActionBar().setTitle("Details");

        schemeNameTV=(TextView)findViewById(R.id.schemeName);
        schemeDetailTV=(TextView)findViewById(R.id.schemeDetails);
        youtubeLinkTV=(TextView)findViewById(R.id.youtubeLink);
        doctorLinkTV=(TextView)findViewById(R.id.doctorLink);

        name= getIntent().getExtras().getString("schemeName");
        details=getIntent().getExtras().getString("schemeDetails");

        schemeNameTV.setText(name);
        schemeDetailTV.setText(details);

        doctorLinkTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri saiUri = Uri.parse("http://pgimer.edu.in/PGIMER_PORTAL/PGIMERPORTAL/home.jsp");

                // Create a new intent to view the SAI URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, saiUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });

        //Setting the spinner for Video language list.
        vidLang = (Spinner) findViewById(R.id.vidSP);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.vid, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vidLang.setAdapter(adapter);
        vidLang.setOnItemSelectedListener(this);

        youtubeLinkTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MedDetails.this, "This will send the user to a videofor further explanations. ", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(MedDetails.this,VidPlayer.class);
                intent.putExtra("lang",vid);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Spinner spinner = (Spinner) adapterView;
        vid = adapterView.getItemAtPosition(i).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
