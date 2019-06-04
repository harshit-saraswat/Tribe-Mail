package com.example.codezero.mysqldb;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

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

public class MedicalChooser extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner sp_gender, sp_symptom1, sp_symptom2, sp_symptom3, sp_symptom4;
    String gender, symptom1, symptom2, symptom3, symptom4;
    String JSON_STRING_SCHEME="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_chooser);

        getSupportActionBar().setTitle("Medical Help Finder");

        //Setting the spinner for gender list.
        sp_gender = (Spinner) findViewById(R.id.genderSP);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_gender.setAdapter(adapter);
        sp_gender.setOnItemSelectedListener(this);

        //Setting the spinner for qualification list.
        sp_symptom1 = (Spinner) findViewById(R.id.symptom1SP);
        adapter = ArrayAdapter.createFromResource(this, R.array.symptoms, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_symptom1.setAdapter(adapter);
        sp_symptom1.setOnItemSelectedListener(this);


        //Setting the spinner for qualification list.
        sp_symptom2 = (Spinner) findViewById(R.id.symptom2SP);
        adapter = ArrayAdapter.createFromResource(this, R.array.symptoms, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_symptom2.setAdapter(adapter);
        sp_symptom2.setOnItemSelectedListener(this);

        //Setting the spinner for qualification list.
        sp_symptom3 = (Spinner) findViewById(R.id.symptom3SP);
        adapter = ArrayAdapter.createFromResource(this, R.array.symptoms, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_symptom3.setAdapter(adapter);
        sp_symptom3.setOnItemSelectedListener(this);

        //Setting the spinner for qualification list.
        sp_symptom4 = (Spinner) findViewById(R.id.symptom4SP);
        adapter = ArrayAdapter.createFromResource(this, R.array.symptoms, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_symptom4.setAdapter(adapter);
        sp_symptom4.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Spinner spinner = (Spinner) adapterView;

        switch (spinner.getId()) {
            case R.id.genderSP:
                gender = adapterView.getItemAtPosition(i).toString();
                break;
            case R.id.symptom1SP:
                symptom1 = adapterView.getItemAtPosition(i).toString();
                break;
            case R.id.symptom2SP:
                symptom2 = adapterView.getItemAtPosition(i).toString();
                break;
            case R.id.symptom3SP:
                symptom3 = adapterView.getItemAtPosition(i).toString();
                break;
            case R.id.symptom4SP:
                symptom4 = adapterView.getItemAtPosition(i).toString();
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    //On click method for searching hospitals
    public void onSearchHospitals(View view){

        // Search for hospitals nearby
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=hospitals");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    //On click method for search options button.
    public void onSearchOptions(View view){
        String tag1,tag2,tag3,tag4;
        String type="getMeds";

        tag1 = symptom1;
        tag2 = symptom2;
        tag3 = symptom3;
        tag4 = symptom4;

        Toast.makeText(this, "Your selected symptoms are: "+symptom1+", "+symptom2+", "+symptom3+", "+symptom4, Toast.LENGTH_LONG).show();
        BackgroundTask backgroundTask = new BackgroundTask();
        backgroundTask.execute(type,tag1,tag2,tag3,tag4);
    }

    //BackgroundTask class to get JSON for and thus getting data from the Server.
    class BackgroundTask extends AsyncTask<String, Void, String> {

        String json_schemes_url;

        @Override
        protected String doInBackground(String... params) {

            String type = params[0];
            if (type.equals("getMeds")){

                try {

                    String tag1 = params[1];
                    String tag2 = params[2];
                    String tag3 = params[3];
                    String tag4 = params[4];


                    URL url = new URL(json_schemes_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);

                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("tag1", "UTF-8") + "=" + URLEncoder.encode(tag1, "UTF-8")+"&"
                            +URLEncoder.encode("tag2", "UTF-8") + "=" + URLEncoder.encode(tag2, "UTF-8")+"&"
                            +URLEncoder.encode("tag3", "UTF-8") + "=" + URLEncoder.encode(tag3, "UTF-8")+"&"
                            +URLEncoder.encode("tag4", "UTF-8") + "=" + URLEncoder.encode(tag4, "UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

                    StringBuilder stringBuilder = new StringBuilder();

                    while ((JSON_STRING_SCHEME = bufferedReader.readLine()) != null) {

                        stringBuilder.append(JSON_STRING_SCHEME + "\n");

                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();

                    return stringBuilder.toString().trim();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPreExecute() {

            json_schemes_url = "http://192.168.43.213/med_data.php";
        }

        @Override
        protected void onPostExecute(String result) {

            if(result.contains("tags")){
                Intent intent = new Intent(MedicalChooser.this,MedicalList.class);
                intent.putExtra("json",result);
                intent.putExtra("actionBar","Disease List");
                startActivity(intent);

            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {

            super.onProgressUpdate(values);
        }
    }
}
