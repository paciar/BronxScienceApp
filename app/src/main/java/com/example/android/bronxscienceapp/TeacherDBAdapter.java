package com.example.android.bronxscienceapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TeacherDBAdapter {
    TeacherDBHelper myhelper;

    public TeacherDBAdapter(Context context) {
        myhelper = new TeacherDBHelper(context);
    }

    public void insertData(String teacher/*, String pass, String contactinfo*/) {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TeacherDBHelper.TEACHER, teacher);
        //contentValues.put(TeacherDBHelper.MyPASSWORD, pass);
        //contentValues.put(myDbHelper.MyCONTACT, contactinfo);
        long id = dbb.insert(TeacherDBHelper.TABLE_NAME, null, contentValues);
        //return id;
    }

    public String getData()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {TeacherDBHelper.UID,TeacherDBHelper.TEACHER};
        Cursor cursor =db.query(TeacherDBHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndex(TeacherDBHelper.UID));
            String name =cursor.getString(cursor.getColumnIndex(TeacherDBHelper.TEACHER));
            //String  password =cursor.getString(cursor.getColumnIndex(myDbHelper.MyPASSWORD));
            buffer.append(cid+ "   " + name + "   " + " \n");
        }
        return buffer.toString();
    }


    static class TeacherDBHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "myDatabase";    // Database Name
        private static final String TABLE_NAME = "myTable";   // Table Name
        private static final int DATABASE_Version = 1;    // Database Version
        private static final String UID = "_id";     // Column I (Primary Key)
        private static final String TEACHER = "Teacher";    //Column II
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                " (\"+UID+\" INTEGER PRIMARY KEY AUTOINCREMENT, \"+TEACHER+\" VARCHAR(255));";
        //private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
        //        " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(255) ,"+ MyPASSWORD+" VARCHAR(225))"+MyCONTACT+" VARCHAR(225));";
        //private static final String TABLE_NAME2 = "myTable2";   // Table Name
        //private static final String DROP_TABLE2 = "DROP TABLE IF EXISTS " + TABLE_NAME2;
        //private static final String DATABASE_NAME2 = "myDatabase2";    // Database Name

        //private static final int DATABASE_Version2 = 1;    // Database Version
        //rivate static final String UserID = "_id";     // Column I (Primary Key)
        //private static final String Item_Name = "Item_Name";    //Column II
        //private static final String Item_Desc = "Item_Description";    // Column III
        //private static final String Item_Image_url = "Image";
        //private static final String CREATE_TABLE2 = "CREATE TABLE " + TABLE_NAME2 +
            //    " (" + UserID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Item_Name + " VARCHAR(255) ," + Item_Desc + " VARCHAR(225))" + Item_Image_url + " VARCHAR(225));";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        private Context context;


        public TeacherDBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context = context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                Message.message(context, "" + e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Message.message(context, "OnUpgrade");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            } catch (Exception e) {
                Message.message(context, "" + e);
            }
        }
    }
}