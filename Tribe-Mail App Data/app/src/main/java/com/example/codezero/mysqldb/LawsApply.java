package com.example.codezero.mysqldb;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class LawsApply extends AppCompatActivity {

    EditText name_ET,fatherName_ET,address_ET,contact_ET,email_ET,place_ET,details_ET;
    TextView dob_TV, web_link;
    Calendar mDOB;
    int day,month,year;

    String name,fatherName,address,contact,email,place,details,dob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laws_apply);

        //Setting up various views.
        name_ET=(EditText)findViewById(R.id.name_ET);
        fatherName_ET=(EditText)findViewById(R.id.fatherName_ET);
        address_ET=(EditText)findViewById(R.id.address_ET);
        contact_ET=(EditText)findViewById(R.id.contact_ET);
        email_ET=(EditText)findViewById(R.id.email_ET);
        place_ET=(EditText)findViewById(R.id.place_ET);
        details_ET=(EditText)findViewById(R.id.details_ET);

        dob_TV=(TextView)findViewById(R.id.dob_TV);
        mDOB=Calendar.getInstance();
        day=mDOB.get(Calendar.DAY_OF_MONTH);
        month=mDOB.get(Calendar.MONTH);
        year=mDOB.get(Calendar.YEAR);
        month+=1;

        dob_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog mDatePickerDialog=new DatePickerDialog(LawsApply.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        int month=monthOfYear+1;
                        dob=dayOfMonth+"/"+month+"/"+year;
                        dob_TV.setText(dob);
                    }
                },year,month,day);
                mDatePickerDialog.show();
            }
        });

        web_link=(TextView)findViewById(R.id.websiteLink);
        web_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri saiUri = Uri.parse("http://chandigarhpolice.gov.in/");

                // Create a new intent to view the SAI URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, saiUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });
    }

    public void onSubmit(View view){

        name=name_ET.getText().toString();
        fatherName=fatherName_ET.getText().toString();
        address=address_ET.getText().toString();
        contact=contact_ET.getText().toString();
        email=email_ET.getText().toString();
        place=place_ET.getText().toString();
        details=details_ET.getText().toString();

        if (name.length() == 0 || dob.length() == 0 || fatherName.length() == 0 || contact.length() == 0 || address.length() == 0 || email.length() == 0 || place.length() == 0 || details.length() == 0 ) {

            Toast.makeText(this, "One or more fields may be empty.Please fill them.", Toast.LENGTH_LONG).show();

        } else if (contact.length() > 10||contact.length()<10) {

            Toast.makeText(this, "Contact number should be equal to 10 digits.", Toast.LENGTH_LONG).show();

        } else {
            String type="fir";
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute(type, name, fatherName, address, contact, email, place, details, dob);

           // Toast.makeText(this, "Your input is being uploaded to our server. For any queries email us at igp-chd@nic.in", Toast.LENGTH_LONG).show();
        }
    }
}
