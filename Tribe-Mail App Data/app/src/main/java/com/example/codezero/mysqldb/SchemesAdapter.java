package com.example.codezero.mysqldb;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Harshit Saraswat on 16-03-2018.
 */

public class SchemesAdapter extends ArrayAdapter<SchemesData> {


    public SchemesAdapter(Activity context, ArrayList<SchemesData> schemes) {
        super(context, 0, schemes);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.row_layout, parent, false);
        }

        SchemesData currentScheme = getItem(position);

        TextView name_tv = (TextView) listItemView.findViewById(R.id.nameTV);
        name_tv.setText(currentScheme.getName());

        return listItemView;
    }

}

