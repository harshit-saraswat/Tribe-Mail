package com.example.codezero.mysqldb;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SchemesListActivity extends AppCompatActivity {

    String SCHEMES_JSON="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schemes_list);

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
            for (int i = 0; i < userArray.length(); i++) {
                JSONObject jsonObject = userArray.getJSONObject(i);
                tags = jsonObject.getString("tags");
                name = jsonObject.getString("name");
                details = jsonObject.getString("details");

                SchemesData schemesData = new SchemesData(tags,name,details);
                schemes.add(schemesData);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListView schemesListView = (ListView)findViewById(R.id.list);

        final SchemesAdapter adapter = new SchemesAdapter(this,schemes);

        schemesListView.setAdapter(adapter);

        schemesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // Find the current scheme data that was clicked on
                SchemesData currentSchemes = adapter.getItem(position);

                Intent intent= new Intent(SchemesListActivity.this,SchemeDetails.class);
                intent.putExtra("schemeName",currentSchemes.getName());
                intent.putExtra("schemeDetails",currentSchemes.getDetails());
                startActivity(intent);
            }
        });

    }
}
