package com.example.codezero.mysqldb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MedicalList extends AppCompatActivity {

    String SCHEMES_JSON="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_list);

        String title=getIntent().getExtras().getString("actionBar");
        getSupportActionBar().setTitle(title);

        SCHEMES_JSON=getIntent().getExtras().getString("json");

        ArrayList<SchemesData> schemes  = new ArrayList<>();


        try {
            JSONObject root = new JSONObject(SCHEMES_JSON);
            JSONArray userArray = root.getJSONArray("server_response");

            String name = "";
            String details = "";
            String tags = "";
            int img=-1;
            for (int i = 0; i < userArray.length(); i++) {
                JSONObject jsonObject = userArray.getJSONObject(i);
                tags = jsonObject.getString("tags");
                name = jsonObject.getString("name");
                details = jsonObject.getString("details");

                if(name.contains("Diarrhoea")){
                    img=R.drawable.diar;
                }
                else if(name.contains("Food")){
                    img=R.drawable.fp;
                }
                else if(name.contains("Malaria")){
                    img=R.drawable.malaria;
                }
                else if(name.contains("HIV")){
                    img=R.drawable.hiv;
                }
                else if(name.contains("Typhoid")){
                    img=R.drawable.typhoid;
                }
                else if(name.contains("Tuberculosis")){
                    img=R.drawable.tub;
                }
                else if(name.contains("Jaundice")){
                    img=R.drawable.jaundice;
                }
                SchemesData schemesData = new SchemesData(tags,name,details,img);
                schemes.add(schemesData);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListView schemesListView = (ListView)findViewById(R.id.list);

        final MedicalAdapter adapter = new MedicalAdapter(this,schemes);

        schemesListView.setAdapter(adapter);

        schemesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // Find the current scheme data that was clicked on
                SchemesData currentSchemes = adapter.getItem(position);

                Intent intent= new Intent(MedicalList.this,MedDetails.class);
                intent.putExtra("schemeName",currentSchemes.getName());
                intent.putExtra("schemeDetails",currentSchemes.getDetails());
                startActivity(intent);
            }
        });

    }
}
