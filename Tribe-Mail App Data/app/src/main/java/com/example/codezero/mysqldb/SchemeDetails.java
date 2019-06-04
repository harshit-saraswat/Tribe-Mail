package com.example.codezero.mysqldb;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SchemeDetails extends AppCompatActivity {

    TextView schemeNameTV,schemeDetailTV,youtubeLinkTV;
    String name,details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheme_details);

        getSupportActionBar().setTitle("Details");

        schemeNameTV=(TextView)findViewById(R.id.schemeName);
        schemeDetailTV=(TextView)findViewById(R.id.schemeDetails);
        youtubeLinkTV=(TextView)findViewById(R.id.youtubeLink);

        name= getIntent().getExtras().getString("schemeName");
        details=getIntent().getExtras().getString("schemeDetails");

        schemeNameTV.setText(name);
        schemeDetailTV.setText(details);

        youtubeLinkTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.contains("Scholarship")){
                    Toast.makeText(SchemeDetails.this, "Redirecting to National Scholarships Portal.", Toast.LENGTH_LONG).show();
                    // Convert the String URL into a URI object (to pass into the Intent constructor)
                    Uri saiUri = Uri.parse("https://scholarships.gov.in/newstdRegfrmInstruction");

                    // Create a new intent to view the Training Centres URI
                    Intent websiteIntent = new Intent(Intent.ACTION_VIEW, saiUri);

                    // Send the intent to launch a new activity
                    startActivity(websiteIntent);
                }

                else{
                    Toast.makeText(SchemeDetails.this, "A link to a certain government portal can be provided.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
