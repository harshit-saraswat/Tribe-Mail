package com.example.codezero.mysqldb;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

import static java.lang.Thread.sleep;

public class SchemeChooser extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    String JSON_STRING_SCHEME;
    Spinner sp_gender, sp_qualifications, sp_occupations, sp_interests;
    String gender, qualification, occupation, interest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheme_chooser);

        getSupportActionBar().setTitle("Scheme Finder");

        //Setting the spinner for gender list.
        sp_gender = (Spinner) findViewById(R.id.genderSP);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_gender.setAdapter(adapter);
        sp_gender.setOnItemSelectedListener(this);

        //Setting the spinner for qualification list.
        sp_qualifications = (Spinner) findViewById(R.id.qualificationSP);
        adapter = ArrayAdapter.createFromResource(this, R.array.qualifications, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_qualifications.setAdapter(adapter);
        sp_qualifications.setOnItemSelectedListener(this);

        //Setting the spinner for occupation list.
        sp_occupations = (Spinner) findViewById(R.id.occupationSP);
        adapter = ArrayAdapter.createFromResource(this, R.array.occupations, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_occupations.setAdapter(adapter);
        sp_occupations.setOnItemSelectedListener(this);

        //Setting the spinner for interest list.
        sp_interests = (Spinner) findViewById(R.id.interestSP);
        adapter = ArrayAdapter.createFromResource(this, R.array.interests, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_interests.setAdapter(adapter);
        sp_interests.setOnItemSelectedListener(this);

    }

    //On click method for Search Schemes button.
    public void onSearchSchemes(View view) {

        String type = "getSchemes";
        String tag1, tag2, tag3, tag4;

        if (gender.contains("Select") || qualification.contains("Select") || interest.contains("Select")) {
            gender = "Male";
            qualification = "Twelfth";
            interest = "Nil";
        }

        if (occupation.contains("Select")) {
            Toast.makeText(this, "Select the valid usertype", Toast.LENGTH_SHORT).show();
        } else {
            tag1 = gender;
            tag2 = qualification;
            tag3 = occupation;
            tag4 = interest;

            BackgroundTask backgroundTask = new BackgroundTask();
            backgroundTask.execute(type, tag1, tag2, tag3, tag4);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Spinner spinner = (Spinner) adapterView;

        switch (spinner.getId()) {
            case R.id.genderSP:
                gender = adapterView.getItemAtPosition(i).toString();
                break;
            case R.id.qualificationSP:
                qualification = adapterView.getItemAtPosition(i).toString();
                break;
            case R.id.occupationSP:
                occupation = adapterView.getItemAtPosition(i).toString();
                break;
            case R.id.interestSP:
                interest = adapterView.getItemAtPosition(i).toString();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    //BackgroundTask class to get JSON for and thus getting data from the Server.
    class BackgroundTask extends AsyncTask<String, Void, String> {

        String json_schemes_url;

        @Override
        protected String doInBackground(String... params) {

            String type = params[0];
            if (type.equals("getSchemes")) {

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
                    String post_data = URLEncoder.encode("tag1", "UTF-8") + "=" + URLEncoder.encode(tag1, "UTF-8") + "&"
                            + URLEncoder.encode("tag2", "UTF-8") + "=" + URLEncoder.encode(tag2, "UTF-8") + "&"
                            + URLEncoder.encode("tag3", "UTF-8") + "=" + URLEncoder.encode(tag3, "UTF-8") + "&"
                            + URLEncoder.encode("tag4", "UTF-8") + "=" + URLEncoder.encode(tag4, "UTF-8");
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

            json_schemes_url = "http://192.168.43.213/json_get_schemes.php";
        }

        @Override
        protected void onPostExecute(String result) {

            if (result.contains("tags")) {
                Intent intent = new Intent(SchemeChooser.this, SchemesListActivity.class);
                intent.putExtra("json", result);
                intent.putExtra("actionBar", "Schemes List");
                startActivity(intent);

            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {

            super.onProgressUpdate(values);
        }
    }
}
