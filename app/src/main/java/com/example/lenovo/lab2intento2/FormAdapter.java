package com.example.lenovo.lab2intento2;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lenovo.lab2intento2.Modelos.Forms;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by lenovo on 10-04-2018.
 */

public class FormAdapter extends ArrayAdapter<Forms> {
    private Context mContext;
    private List<Forms> formList = new ArrayList<>();

    public FormAdapter(Context context, ArrayList<Forms> list) {
        super(context, 0 , list);
        mContext = context;
        formList = list;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.formitemview, parent, false);
        }

        // Get the data item for this position
        Forms form = getItem(position);

        // Lookup view for data population
        TextView name = (TextView) convertView.findViewById(R.id.nombreform);
        TextView date = (TextView) convertView.findViewById(R.id.fechaform);
        TextView comment = (TextView) convertView.findViewById(R.id.descripcionform);
        // Populate the data into the template view using the data object
        name.setText(form.getName());
        date.setText(form.getDate());
        comment.setText(form.getComment());
        // Return the completed view to render on screen
        return convertView;
    }
}
