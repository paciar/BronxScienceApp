package com.example.android.bronxscienceapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddCourse extends AppCompatActivity {
    private EditText mCourseName,mCourseDes,mCoursePre,mCourseMajor;
    private Button add,back;
    private CourseHelper mDatabase;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item);
        mDatabase = new CourseHelper(this);

        mCourseName=findViewById(R.id.edit_name);
        mCourseDes=findViewById(R.id.edit_des);
        mCoursePre=findViewById(R.id.edit_pre);
        mCourseMajor=findViewById(R.id.edit_major);

        add=findViewById(R.id.add_button);
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
                    finish();
                    getFragmentManager().popBackStackImmediate();
                }
            }
        });

        back=findViewById(R.id.back_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStackImmediate();
            }
        });
    }

    public void showMessage (String title, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}

