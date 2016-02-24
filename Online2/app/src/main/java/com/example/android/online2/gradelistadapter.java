package com.example.android.online2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by VIVEK on 24-02-2016.
 */
public class gradelistadapter extends BaseAdapter{

    private Context context;
    private List<grades> gradesList;

    public gradelistadapter(Context context, List<grades> gradesList) {
        this.context = context;
        this.gradesList = gradesList;
    }

    @Override
    public int getCount() {
        return gradesList.size();
    }

    @Override
    public Object getItem(int position) {
        return gradesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context,R.layout.item_lsit,null);
        TextView course= (TextView) v.findViewById(R.id.code);
        TextView desc= (TextView) v.findViewById(R.id.description);
        TextView weight= (TextView) v.findViewById(R.id.weightage);
        TextView name = (TextView) v.findViewById(R.id.name);

        course.setText(gradesList.get(position).getCourse());
        desc.setText(gradesList.get(position).getDescr());
        weight.setText(gradesList.get(position).getWeightage());
        name.setText(gradesList.get(position).getName());

        v.setTag(gradesList.get(position).getId());
        return v;
    }
}
