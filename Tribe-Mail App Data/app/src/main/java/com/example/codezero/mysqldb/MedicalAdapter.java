package com.example.codezero.mysqldb;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Harshit Saraswat on 22-03-2018.
 */

public class MedicalAdapter extends ArrayAdapter<SchemesData> {


    public MedicalAdapter(Activity context, ArrayList<SchemesData> schemes) {
        super(context, 0, schemes);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.med_row, parent, false);
        }

        SchemesData currentScheme = getItem(position);

        TextView name_tv = (TextView) listItemView.findViewById(R.id.diseaseName);
        name_tv.setText(currentScheme.getName());

        ImageView icon_iv = (ImageView) listItemView.findViewById(R.id.icon_img);
        icon_iv.setImageResource(currentScheme.getImageResource());

        return listItemView;
    }

}


