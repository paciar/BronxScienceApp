package com.example.android.bronxscienceapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;

public class CourseDetailFragment extends Fragment {
    private TextView mViewName, mViewDes, mViewPre,mViewMajor;
    private Button delete, back;
    private ArrayList<Course> mItemList;

    public CourseDetailFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_course_detail,container,false);
        CourseHelper mDatabase=new CourseHelper(getActivity());
        int position=getArguments().getInt("position");
        String id=getArguments().getString("id");

        mItemList= mDatabase.getCourseList();
        Course c=mItemList.get(position);
        mViewName=view.findViewById(R.id.view_name);
        mViewDes=view.findViewById(R.id.view_des);
        mViewPre=view.findViewById(R.id.view_pre);
        mViewMajor=view.findViewById(R.id.view_major);

        mViewName.setText(c.getName());
        mViewDes.setText("Description: "+c.getDescription());
        mViewPre.setText("Prerequisite: "+c.getPrerequisite());
        mViewMajor.setText("Major: "+c.getMajor());

        delete = (Button) view.findViewById(R.id.delete_button);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                mDatabase.deleteCourse(id);
                getFragmentManager().popBackStack();
            }
        });

        back = (Button) view.findViewById(R.id.back_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                getFragmentManager().popBackStack();
            }
        });

        return view;
    }

}


