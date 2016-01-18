package com.codepath.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.codepath.models.Task;
import com.codepath.mytodolist.R;

import java.util.ArrayList;

/**
 * Created by carly.baja on 1/12/2016.
 */
public class TasksAdapter extends ArrayAdapter<Task> {

    public TasksAdapter(Context context, ArrayList<Task> tasks) {
        super(context, 0, tasks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Task task = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_tasks, parent, false);
        }
        // Lookup view for data population
        TextView tvTaskDescription = (TextView) convertView.findViewById(R.id.tvTaskDescription);
        TextView tvStatus = (TextView) convertView.findViewById(R.id.tvStatus);
        // Populate the data into the template view using the data object
        tvTaskDescription.setText(task.description);
        tvStatus.setText((CharSequence) task.statu);
        // Return the completed view to render on screen
        return convertView;
    }
}