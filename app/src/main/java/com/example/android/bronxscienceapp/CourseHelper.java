package com.example.android.bronxscienceapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class CourseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Course";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "BXSCI_Roster";
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + CourseContract.FeedEntry.TABLE_NAME + " (" +
                    CourseContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    CourseContract.FeedEntry.NAME + TEXT_TYPE + COMMA_SEP +
                    CourseContract.FeedEntry.DESCRIPTION+ TEXT_TYPE + COMMA_SEP +
                    CourseContract.FeedEntry.COURSEID+ TEXT_TYPE + COMMA_SEP +
                    CourseContract.FeedEntry.PREREQUISITE+ TEXT_TYPE + COMMA_SEP+
                    CourseContract.FeedEntry.MAJOR+ TEXT_TYPE + COMMA_SEP+
                    CourseContract.FeedEntry.TAKEN + " INTEGER" + COMMA_SEP+
                    CourseContract.FeedEntry.QUALIFY + " INTEGER" +
                    " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + CourseContract.FeedEntry.TABLE_NAME;

    public CourseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //get the list of all courses avaliable
    public ArrayList<Course> getCourseList() {
        String sql = "select * from " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Course> courseList = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id=Integer.parseInt(cursor.getString(0));
                String name=cursor.getString(cursor.getColumnIndexOrThrow(CourseContract.FeedEntry.NAME));
                String des=cursor.getString(cursor.getColumnIndexOrThrow(CourseContract.FeedEntry.DESCRIPTION));
                String uId =cursor.getString(cursor.getColumnIndexOrThrow(CourseContract.FeedEntry.COURSEID));
                String pre =cursor.getString(cursor.getColumnIndexOrThrow(CourseContract.FeedEntry.PREREQUISITE));
                String major =cursor.getString(cursor.getColumnIndexOrThrow(CourseContract.FeedEntry.MAJOR));
                int t = cursor.getInt(cursor.getColumnIndexOrThrow(CourseContract.FeedEntry.TAKEN));
                int q = cursor.getInt(cursor.getColumnIndexOrThrow(CourseContract.FeedEntry.QUALIFY));

                courseList.add(new Course(name,des,pre,uId,major,t==1,q==1));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return courseList;
    }

    //get list of all courses with keyword of major
    // assume that each course can only have one major for now
    public ArrayList<Course> getMajorList(String selectedMajor) {
        String sql = "select * from " + TABLE_NAME + "where major="+selectedMajor;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Course> majorList = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id=Integer.parseInt(cursor.getString(0));
                String name=cursor.getString(cursor.getColumnIndexOrThrow(CourseContract.FeedEntry.NAME));
                String des=cursor.getString(cursor.getColumnIndexOrThrow(CourseContract.FeedEntry.DESCRIPTION));
                String uId =cursor.getString(cursor.getColumnIndexOrThrow(CourseContract.FeedEntry.COURSEID));
                String pre =cursor.getString(cursor.getColumnIndexOrThrow(CourseContract.FeedEntry.PREREQUISITE));
                String major =cursor.getString(cursor.getColumnIndexOrThrow(CourseContract.FeedEntry.MAJOR));
                int t = cursor.getInt(cursor.getColumnIndexOrThrow(CourseContract.FeedEntry.TAKEN));
                int q = cursor.getInt(cursor.getColumnIndexOrThrow(CourseContract.FeedEntry.QUALIFY));

                majorList.add(new Course(name,des,pre,uId,major, t==1,q==1));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return majorList;
    }

    public ArrayList<String> getMajorChoices(){
        String sql ="SELECT DISTINCT MAJOR FROM "+TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> majorChoice = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id=Integer.parseInt(cursor.getString(0));
                String name=cursor.getString(cursor.getColumnIndexOrThrow(CourseContract.FeedEntry.NAME));
                majorChoice.add(name);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return majorChoice;
    }

    void addCourse(Course course) {
        ContentValues values = new ContentValues();
        values.put(CourseContract.FeedEntry.NAME, course.getName());
        values.put(CourseContract.FeedEntry.DESCRIPTION, course.getDescription());
        values.put(CourseContract.FeedEntry.PREREQUISITE,course.getPrerequisite());
        values.put(CourseContract.FeedEntry.MAJOR,course.getMajor());
        values.put(CourseContract.FeedEntry.TAKEN,course.getTaken());
        values.put(CourseContract.FeedEntry.QUALIFY,course.getQualified());
        values.put(CourseContract.FeedEntry.COURSEID,course.getId());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}


