package com.example.codezero.mysqldb;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class GetHelpFragment extends Fragment {

    TextView hospitals,police,sai,ngo,emrs;

    public GetHelpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_get_help, container, false);

        hospitals=(TextView)view.findViewById(R.id.findHospitals);
        police=(TextView)view.findViewById(R.id.findPolice);
        sai=(TextView)view.findViewById(R.id.findSAI);
        ngo=(TextView)view.findViewById(R.id.findNGO);
        emrs=(TextView)view.findViewById(R.id.findEMRS);

        hospitals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Search for hospitals nearby
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=hospitals");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        police.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Search for police stations nearby
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=police+station");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        sai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Search for SAI centres nearby
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=sports+authority+of+india");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        ngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Search for NGOs nearby
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=NGO");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        emrs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Search for EMRS nearby
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=Eklavya+model+residental+school");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        return view;
    }
}
