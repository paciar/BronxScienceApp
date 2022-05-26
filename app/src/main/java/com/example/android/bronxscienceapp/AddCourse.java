package com.example.android.bronxscienceapp;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AddCourse extends Fragment {
    private EditText mCourseName,mCourseDes,mCoursePre,mCourseMajor;
    private Button add,back;
    private CourseHelper mDatabase;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.add_item, container,false);
        mDatabase = new CourseHelper(getActivity());

        mCourseName=view.findViewById(R.id.edit_name);
        mCourseDes=view.findViewById(R.id.edit_des);
        mCoursePre=view.findViewById(R.id.edit_pre);
        mCourseMajor=view.findViewById(R.id.edit_major);

        add=view.findViewById(R.id.add_button);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name=mCourseName.getText().toString();
                final String des=mCourseDes.getText().toString();
                final String pre=mCoursePre.getText().toString();
                final String major=mCourseMajor.getText().toString();

                if(name.trim().isEmpty() || des.trim().isEmpty() ||
                        pre.trim().isEmpty() || des.trim().isEmpty()){
                    showMessage("Incomplete fields","Make sure you fill in all required fields and add a picture");
                }
                else {
                    //TODO: set taken and qualified to false for now, later have methods to check
                    Course c = new Course(name,des,pre,major,false,false);
                    mDatabase.addCourse(c);

                    //TODO: fix view problem
                    getActivity().getSupportFragmentManager().popBackStack();
                }
            }
        });

        back=view.findViewById(R.id.back_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStackImmediate();
            }
        });
        return view;
    }

    public void showMessage (String title, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}

