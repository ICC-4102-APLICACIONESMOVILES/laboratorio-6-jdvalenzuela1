package com.example.lenovo.lab2intento2;

import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.Context;
import com.example.lenovo.lab2intento2.Modelos.Answers;
import java.util.ArrayList;
import java.util.List;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by lenovo on 08-05-2018.
 */

public class AnswersAdapter extends ArrayAdapter<Answers> {
    private Context mContext;
    private List<Answers> answersList = new ArrayList<>();

    public AnswersAdapter(Context context, ArrayList<Answers> list) {
        super(context, 0 , list);
        mContext = context;
        answersList = list;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.answersitemview , parent, false);
        }

        // Get the data item for this position
        Answers answer = getItem(position);

        // Lookup view for data population
        TextView questionId = (TextView) convertView.findViewById(R.id.questionId);
        TextView fechaAnswer = (TextView) convertView.findViewById(R.id.fechaAnswer);
        TextView location = (TextView) convertView.findViewById(R.id.location);
        // Populate the data into the template view using the data object
        questionId.setText(answer.getQuestionId());
        fechaAnswer.setText(answer.getDate());
        location.setText(answer.getLatitude().toString() + ','+answer.getLongitude().toString());
        // Return the completed view to render on screen
        return convertView;
    }
}
