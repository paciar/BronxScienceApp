package com.example.android.bronxscienceapp;

import android.graphics.Bitmap;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

public class Course {
    private String mId; //UUID.randomUUID().toString()
    private String mName;
    private String mDescription;
    private String mPrerequisite; //need to use dictionary instead
    private String mMajor;
    private boolean mQualified;
    private boolean mTaken;

    //used in AddCourse, initialize a new Course Object
    public Course(String name, String des, String pre, String major,
                  boolean taken,boolean qualified) {
        mName=name;
        mDescription=des;
        mPrerequisite=pre;
        mMajor=major;
        mTaken=taken;
        mQualified=qualified;
        mId=UUID.randomUUID().toString();
    }

    //used in CourseHelper, take info from database and store into a Course Object
    public Course(String name, String des, String pre, String id, String major,
                  boolean taken,boolean qualified) {
       mName=name;
       mDescription=des;
       mPrerequisite=pre;
       mMajor=major;
       mTaken=taken;
       mQualified=qualified;
       mId=id;
    }

    public String getId() { return mId; }

    public String getName() { return mName; }
    public void setName(String name) { mName=name; }

    public String getDescription() { return mDescription; }
    public void setDescription(String des) { mDescription=des; }

    public String getPrerequisite(){return mPrerequisite;}
    public void setPrerequisite(String pre){ mPrerequisite=pre;}

    public String getMajor(){return mMajor;}
    public void setMajor(String major){ mMajor=major;}

    //TODO: use dictionary instead
    public ArrayList getRequirements(String pre){
        ArrayList<String> preconditions = new ArrayList<>();
        //loop that slice string based on ","
        return preconditions;
    }

    //TODO: use setTaken and setQualified after student input their courses+grades
    public Boolean getTaken(){ return mTaken;}
    public void setTaken(boolean taken){ mTaken=taken;}

    public Boolean getQualified(){ return mQualified;}
    public void setQualified(boolean qualify){ mQualified=qualify;}

}


