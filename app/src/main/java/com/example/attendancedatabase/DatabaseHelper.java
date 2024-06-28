package com.example.attendancedatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_TABLE = "CREATE TABLE";
    public static final String TEACHERS = "TEACHERS";
    public static final String COL_SNAME = "NAME";
    public static final String COL_NAME = COL_SNAME;
    public static final String COL_SUBJECT = "SUBJECT";
    public static final String FYSTUDENTS = "FYSTUDENTS";
    public static final String SYSTUDENTS = "SYSTUDENTS";
    public static final String TYSTUDENTS = "TYSTUDENTS";
    public static final String ID = "ID";
    public static final String CLASS = "CLASS";
    public static final String DIV = "DIV";
    public static final String ROLL = "ROLLNO";
    public static final String ADDED_BY = "ADDED_BY";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "AttendDatabase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTeacherTable = CREATE_TABLE + " " + TEACHERS + "(" + COL_NAME + " TEXT NOT NULL PRIMARY KEY, " + COL_SUBJECT + " TEXT, " + ADDED_BY + " TEXT);";
        String createFYStudentTable = CREATE_TABLE+ " " + FYSTUDENTS + "(" +
                ID + " TEXT NOT NULL PRIMARY KEY, " +
                COL_SNAME + " TEXT, " +
                CLASS + " TEXT, " +
                DIV + " TEXT, " +
                ROLL + " NUMBER NOT NULL, " +
                ADDED_BY + " TEXT," +
                "OVERALL REAL," +
                "PYTHON REAL," +
                "COD REAL," +
                "STATISTIC REAL," +
                "LINUX REAL," +
                "CPP REAL," +
                "HTML REAL," +
                "CALCULUS REAL" + ");";
        String createPYSubjectTable = CREATE_TABLE + " " + "PYTHON" + "("+
                ID + " TEXT," +
                "PRESENT TEXT," +
                "ADDEDDATE DATE," +
                "MONTH TEXT," +
                "ADDEDTIME TIME," +
                ADDED_BY + "TEXT" + ");";
        String createCODSubjectTable = CREATE_TABLE + " " + "COD" + "("+
                ID + " TEXT," +
                "PRESENT TEXT," +
                "ADDEDDATE DATE," +
                "MONTH TEXT," +
                "ADDEDTIME TIME," +
                ADDED_BY + "TEXT" + ");";
        String createSTATSubjectTable = CREATE_TABLE + " " + "STATISTIC" + "("+
                ID + " TEXT," +
                "PRESENT TEXT," +
                "ADDEDDATE DATE," +
                "MONTH TEXT," +
                "ADDEDTIME TIME," +
                ADDED_BY + "TEXT" + ");";
        String createLINUXSubjectTable = CREATE_TABLE + " " + "LINUX" + "("+
                ID + " TEXT," +
                "PRESENT TEXT," +
                "ADDEDDATE DATE," +
                "MONTH TEXT," +
                "ADDEDTIME TIME," +
                ADDED_BY + "TEXT" + ");";
        String createCPPSubjectTable = CREATE_TABLE + " " + "CPP" + "("+
                ID + " TEXT," +
                "PRESENT TEXT," +
                "ADDEDDATE DATE," +
                "MONTH TEXT," +
                "ADDEDTIME TIME," +
                ADDED_BY + "TEXT" + ");";
        String createHTMLSubjectTable = CREATE_TABLE + " " + "HTML" + "("+
                ID + " TEXT," +
                "PRESENT TEXT," +
                "ADDEDDATE DATE," +
                "MONTH TEXT," +
                "ADDEDTIME TIME," +
                ADDED_BY + "TEXT" + ");";
        String createCALSubjectTable = CREATE_TABLE + " " + "CALCULUS" + "("+
                ID + " TEXT," +
                "PRESENT TEXT," +
                "ADDEDDATE DATE," +
                "MONTH TEXT," +
                "ADDEDTIME TIME," +
                ADDED_BY + "TEXT" + ");";

        String createSYStudentTable = CREATE_TABLE+ " " + SYSTUDENTS + "(" +
                ID + " TEXT NOT NULL PRIMARY KEY, " +
                COL_SNAME + " TEXT, " +
                CLASS + " TEXT, " +
                DIV + " TEXT, " +
                ROLL + " NUMBER NOT NULL, " +
                ADDED_BY + " TEXT," +
                "OVERALL REAL," +
                "FOA REAL," +
                "ADV_JAVA REAL," +
                "CN REAL," +
                "SE REAL," +
                "LA REAL," +
                "DOTNET REAL," +
                "ANDROID REAL" + ");";
        String createFOASubjectTable = CREATE_TABLE + " " + "FOA" + "("+
                ID + " TEXT," +
                "PRESENT TEXT," +
                "ADDEDDATE DATE," +
                "MONTH TEXT," +
                "ADDEDTIME TIME," +
                ADDED_BY + "TEXT" + ");";
        String createADV_JAVASubjectTable = CREATE_TABLE + " " + "ADV_JAVA" + "("+
                ID + " TEXT," +
                "PRESENT TEXT," +
                "ADDEDDATE DATE," +
                "MONTH TEXT," +
                "ADDEDTIME TIME," +
                ADDED_BY + "TEXT" + ");";
        String createSESubjectTable = CREATE_TABLE + " " + "SE" + "("+
                ID + " TEXT," +
                "PRESENT TEXT," +
                "ADDEDDATE DATE," +
                "MONTH TEXT," +
                "ADDEDTIME TIME," +
                ADDED_BY + "TEXT" + ");";
        String createCNSubjectTable = CREATE_TABLE + " " + "CN" + "("+
                ID + " TEXT," +
                "PRESENT TEXT," +
                "ADDEDDATE DATE," +
                "MONTH TEXT," +
                "ADDEDTIME TIME," +
                ADDED_BY + "TEXT" + ");";
        String createLASubjectTable = CREATE_TABLE + " " + "LA" + "("+
                ID + " TEXT," +
                "PRESENT TEXT," +
                "ADDEDDATE DATE," +
                "MONTH TEXT," +
                "ADDEDTIME TIME," +
                ADDED_BY + "TEXT" + ");";
        String createDOTNETSubjectTable = CREATE_TABLE + " " + "DOTNET" + "("+
                ID + " TEXT," +
                "PRESENT TEXT," +
                "ADDEDDATE DATE," +
                "MONTH TEXT," +
                "ADDEDTIME TIME," +
                ADDED_BY + "TEXT" + ");";
        String createANDROIDSubjectTable = CREATE_TABLE + " " + "ANDROID" + "("+
                ID + " TEXT," +
                "PRESENT TEXT," +
                "ADDEDDATE DATE," +
                "MONTH TEXT," +
                "ADDEDTIME TIME," +
                ADDED_BY + "TEXT" + ");";

        String createTYStudentTable = CREATE_TABLE+ " " + TYSTUDENTS + "(" +
                ID + " TEXT NOT NULL PRIMARY KEY, " +
                COL_SNAME + " TEXT, " +
                CLASS + " TEXT, " +
                DIV + " TEXT, " +
                ROLL + " NUMBER NOT NULL, " +
                ADDED_BY + " TEXT," +
                "OVERALL REAL," +
                "DATA_SCIENCE REAL," +
                "CLOUD_COMPUT REAL," +
                "INFO_RETRIEVAL REAL," +
                "ETHICAL_HACKING REAL," +
                "CYBER_FORENSIC REAL" + ");";
        String createDSSubjectTable = CREATE_TABLE + " " + "DATA_SCIENCE" + "("+
                ID + " TEXT," +
                "PRESENT TEXT," +
                "ADDEDDATE DATE," +
                "MONTH TEXT," +
                "ADDEDTIME TIME," +
                ADDED_BY + "TEXT" + ");";
        String createCCSubjectTable = CREATE_TABLE + " " + "CLOUD_COMPUT" + "("+
                ID + " TEXT," +
                "PRESENT TEXT," +
                "ADDEDDATE DATE," +
                "MONTH TEXT," +
                "ADDEDTIME TIME," +
                ADDED_BY + "TEXT" + ");";
        String createIRSubjectTable = CREATE_TABLE + " " + "INFO_RETRIEVAL" + "("+
                ID + " TEXT," +
                "PRESENT TEXT," +
                "ADDEDDATE DATE," +
                "MONTH TEXT," +
                "ADDEDTIME TIME," +
                ADDED_BY + "TEXT" + ");";
        String createEHSubjectTable = CREATE_TABLE + " " + "ETHICAL_HACKING" + "("+
                ID + " TEXT," +
                "PRESENT TEXT," +
                "ADDEDDATE DATE," +
                "MONTH TEXT," +
                "ADDEDTIME TIME," +
                ADDED_BY + "TEXT" + ");";
        String createCFSubjectTable = CREATE_TABLE + " " + "CYBER_FORENSIC" + "("+
                ID + " TEXT," +
                "PRESENT TEXT," +
                "ADDEDDATE DATE," +
                "MONTH TEXT," +
                "ADDEDTIME TIME," +
                ADDED_BY + "TEXT" + ");";

        String addAdmin = "INSERT INTO " + TEACHERS + " VALUES('Admin', '---', 'Admin')";
        String addAdmin1 = "INSERT INTO " + TEACHERS + " VALUES('Devendra', 'DOTNET, ADV_JAVA', 'Admin')";
        String addAdmin2 = "INSERT INTO " + TEACHERS + " VALUES('Mahendra', 'CN', 'Admin')";
        String addAdmin3 = "INSERT INTO " + TEACHERS + " VALUES('Kunal', 'SE', 'Admin')";
        String addStudent = "INSERT INTO SYSTUDENTS VALUES('3789337','Devendra','SYCS','A',47,'Admin',0,0,0,0,0,0,0,0);";

        db.execSQL(createTeacherTable);

        db.execSQL(createFYStudentTable);
        db.execSQL(createPYSubjectTable);
        db.execSQL(createCODSubjectTable);
        db.execSQL(createSTATSubjectTable);
        db.execSQL(createLINUXSubjectTable);
        db.execSQL(createCPPSubjectTable);
        db.execSQL(createHTMLSubjectTable);
        db.execSQL(createCALSubjectTable);

        db.execSQL(createSYStudentTable);
        db.execSQL(createFOASubjectTable);
        db.execSQL(createADV_JAVASubjectTable);
        db.execSQL(createCNSubjectTable);
        db.execSQL(createSESubjectTable);
        db.execSQL(createLASubjectTable);
        db.execSQL(createDOTNETSubjectTable);
        db.execSQL(createANDROIDSubjectTable);

        db.execSQL(createTYStudentTable);
        db.execSQL(createDSSubjectTable);
        db.execSQL(createCCSubjectTable);
        db.execSQL(createIRSubjectTable);
        db.execSQL(createEHSubjectTable);
        db.execSQL(createCFSubjectTable);

        db.execSQL(addAdmin);
        db.execSQL(addAdmin1);
        db.execSQL(addAdmin2);
        db.execSQL(addAdmin3);
        db.execSQL(addStudent);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
