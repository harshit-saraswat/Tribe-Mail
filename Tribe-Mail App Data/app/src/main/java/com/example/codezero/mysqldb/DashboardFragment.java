package com.example.codezero.mysqldb;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {


    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_dashboard, container, false);

        LinearLayout motaLinearLayout = (LinearLayout) view.findViewById(R.id.motaschemes);
        LinearLayout sportsLinearLayout = (LinearLayout) view.findViewById(R.id.sports);
        LinearLayout medicalLinearLayout = (LinearLayout) view.findViewById(R.id.medical);
        LinearLayout lawLinearLayout = (LinearLayout) view.findViewById(R.id.laws);
        LinearLayout skillsLinearLayout = (LinearLayout) view.findViewById(R.id.skillDev);


        //TextView dataTextView =(TextView) findViewById(R.id.data);
        //Button logoutButton = (Button) view.findViewById(R.id.logoutButton);

        motaLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "MoTA Schemes", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), SchemeChooser.class);
                startActivity(intent);
            }
        });

        sportsLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Sports Authority Services", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), SportsAuthority.class);
                startActivity(intent);
            }
        });

        medicalLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Health and Medical Services", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), MedicalChooser.class);
                startActivity(intent);
            }
        });

        lawLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "HR and Law Services", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), HRAndLaw.class);
                startActivity(intent);
            }
        });

        skillsLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Skill Development Services", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), SkillDevelopment.class);
                startActivity(intent);
            }
        });

        return view;
    }

}
