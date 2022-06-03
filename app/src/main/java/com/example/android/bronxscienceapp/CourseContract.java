package com.example.android.bronxscienceapp;

import android.provider.BaseColumns;

import java.util.UUID;

public final class CourseContract {

    public CourseContract() {}

    public static abstract class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "BXSCI_Roster";

        public static final String NAME = "name";
        public static final String COURSEID = "courseid";
        public static final String DESCRIPTION = "description";
        public static final String PREREQUISITE = "prerequisite";
        public static final String MAJOR = "major";
        public static final String TAKEN = "taken";
        public static final String QUALIFY = "qualify";

        public static final String COLUMN_NAME_NULLABLE = null;
    }
}

