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

public class HRAndLaw extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner sp_gender, sp_laws;
    String gender, laws;
    String JSON_STRING_SCHEME="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrand_law);

        getSupportActionBar().setTitle("HR and Law Services");

        //Setting the spinner for gender list.
        sp_gender = (Spinner) findViewById(R.id.genderSP);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_gender.setAdapter(adapter);
        sp_gender.setOnItemSelectedListener(this);

        //Setting the spinner for qualification list.
        sp_laws = (Spinner) findViewById(R.id.lawsSP);
        adapter = ArrayAdapter.createFromResource(this, R.array.laws, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_laws.setAdapter(adapter);
        sp_laws.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Spinner spinner = (Spinner) adapterView;

        switch (spinner.getId()) {
            case R.id.genderSP:
                gender = adapterView.getItemAtPosition(i).toString();
                break;
            case R.id.lawsSP:
                laws = adapterView.getItemAtPosition(i).toString();
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    //On click method for searching police stations
    public void onSearchPolice(View view){

        // Search for hospitals nearby
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=police+station");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
    //On click method for search laws button.
    public void onSearchLaws(View view){
        Toast.makeText(this, "Your are searching laws for: "+laws, Toast.LENGTH_LONG).show();
        BackgroundTask backgroundTask = new BackgroundTask();
        backgroundTask.execute(laws);
    }

    //On click method for file complaints button
    public void onFileFIR(View view){
        Intent intent = new Intent(this,LawsApply.class);
        startActivity(intent);
    }
    //BackgroundTask class to get JSON for and thus getting data from the Server.
    class BackgroundTask extends AsyncTask<String, Void, String> {

        String json_schemes_url;

        @Override
        protected String doInBackground(String... params) {

                try {

                    String tag1 = params[0];

                    URL url = new URL(json_schemes_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);

                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("tag1", "UTF-8") + "=" + URLEncoder.encode(tag1, "UTF-8");
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
            return null;
        }

        @Override
        protected void onPreExecute() {

            json_schemes_url = "http://192.168.43.213/laws_data.php";
        }

        @Override
        protected void onPostExecute(String result) {

            if(result.contains("tags")){
                Intent intent = new Intent(HRAndLaw.this,SchemesListActivity.class);
                intent.putExtra("json",result);
                intent.putExtra("actionBar","Laws List");
                startActivity(intent);
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {

            super.onProgressUpdate(values);
        }
    }
}
