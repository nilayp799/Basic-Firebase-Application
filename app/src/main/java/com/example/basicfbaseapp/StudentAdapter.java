package com.example.basicfbaseapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<StudentRead> studentsList;

    public StudentAdapter(Context context,ArrayList<StudentRead> studentsList) {
        this.context = context;
        this.studentsList = studentsList;
    }

    @Override
    public int getCount() {
        return studentsList.size();
    }

    @Override
    public Object getItem(int position) {
        return studentsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view ==null){
            view = LayoutInflater.from(context).inflate(R.layout.student_list_item,parent,false);
        }
        ImageView img = view.findViewById(R.id.item_img);
        TextView name = view.findViewById(R.id.item_name);
        TextView enrollment = view.findViewById(R.id.item_enrollment);

        img.setImageResource(R.drawable.ic_baseline_person_outline_24);
        name.setText("Name : "+studentsList.get(position).getName());
        enrollment.setText("Enrollment : "+ studentsList.get(position).getEnrollment());

        return view;
    }
}
