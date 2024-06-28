package com.example.attendancedatabase;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class Student {
    Context context;
    String sID;
    String name;

    Student(Context context){this.context = context;}

    public boolean login(String Id, String pass, String aClass){
        SQLiteDatabase db = new DatabaseHelper(context).getReadableDatabase();
        sID = Id;
        String search = null;
        switch (aClass) {
            case "FYCS":
                search = "SELECT ID FROM FYSTUDENTS;";
                break;
            case "SYCS":
                search = "SELECT ID FROM SYSTUDENTS;";
                break;
            case "TYCS":
                search = "SELECT ID FROM TYSTUDENTS;";
                break;
            default:
                return false;
        }
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(search, null);
        if(cursor.moveToFirst()){
            do {
                String studentID = cursor.getString(0);
                if(studentID.equals(sID)){
                    cursor.close();
                    db.close();
                    return pass.equals("87654321");
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return false;
    }

    public String getName(String aClass, String sID){
        SQLiteDatabase db = new DatabaseHelper(context).getReadableDatabase();
        String search = null;
        switch (aClass) {
            case "FYCS":
                search = "SELECT NAME FROM FYSTUDENTS WHERE ID = '"+sID+"';";
                break;
            case "SYCS":
                search = "SELECT NAME FROM SYSTUDENTS WHERE ID = '"+sID+"';";
                break;
            case "TYCS":
                search = "SELECT NAME FROM TYSTUDENTS WHERE ID = '"+sID+"';";
                break;
        }
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(search, null);
        cursor.moveToFirst();
        name = cursor.getString(0);
        return name;
    }

    public String getAttendance(String col, String aClass, String sID){
        SQLiteDatabase db = new DatabaseHelper(context).getReadableDatabase();
        String search = null;
        switch (aClass) {
            case "FYCS":
                search = "SELECT " + col + " FROM FYSTUDENTS WHERE ID = '"+sID+"';";
                break;
            case "SYCS":
                search = "SELECT " + col + " FROM SYSTUDENTS WHERE ID = '"+sID+"';";
                break;
            case "TYCS":
                search = "SELECT " + col + " FROM TYSTUDENTS WHERE ID = '"+sID+"';";
                break;
        }
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(search, null);
        cursor.moveToFirst();
        return Float.toString(cursor.getFloat(0));
    }

    public String[] getSubjects(String aClass){
        String[] subjects = null;
        switch (aClass) {
            case "FYCS":
                subjects = new String[]{"PYTHON", "COD", "STATISTIC", "LINUX", "CPP", "HTML", "CALCULUS"};
                break;
            case "SYCS":
                subjects = new String[]{"FOA", "ADV_JAVA", "CN", "SE", "LA", "DOTNET", "ANDROID"};
                break;
            case "TYCS":
                subjects = new String[]{"DATA_SCIENCE", "CLOUD_COMPUT", "INFO_RETRIEVAL", "ETHICAL_HACKING", "CYBER_FORENSIC"};
                break;
        }
        return subjects;
    }

    public int getRoll(String aClass, String sID){
        SQLiteDatabase db = new DatabaseHelper(context).getReadableDatabase();
        String search = null;
        switch (aClass) {
            case "FYCS":
                search = "SELECT ROLLNO FROM FYSTUDENTS WHERE ID = '"+sID+"';";
                break;
            case "SYCS":
                search = "SELECT ROLLNO FROM SYSTUDENTS WHERE ID = '"+sID+"';";
                break;
            case "TYCS":
                search = "SELECT ROLLNO FROM TYSTUDENTS WHERE ID = '"+sID+"';";
                break;
        }
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(search, null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    public ArrayList<StudentData> getSubjectAttend(String Id, String sub, String month){
        ArrayList<StudentData> sd = new ArrayList<>();
        String search = null;
        SQLiteDatabase db = new DatabaseHelper(context).getReadableDatabase();
        if (month.equals("Months")){
            search = "SELECT ID, ADDEDDATE, PRESENT FROM '" + sub +"' WHERE ID = '" + Id + "';";
        }
        else {
            search = "SELECT ID, ADDEDDATE, PRESENT FROM '" + sub +"' WHERE MONTH = '" +month+ "' AND ID = '" + Id + "';";
        }
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(search, null);
        if (cursor.moveToFirst()){
            do {
                String id = cursor.getString(0);
                String date = cursor.getString(1);
                String present = cursor.getString(2);
                sd.add(new StudentData(id, date, present));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return sd;
    }

    public void updateOverall(String id, String aClass){
        SQLiteDatabase db = new DatabaseHelper(context).getReadableDatabase();
        String search = null;
        switch (aClass) {
            case "FYCS":
                search = "SELECT PYTHON, COD, STATISTIC,LINUX, CPP, CALCULUS HTML FROM FYSTUDENTS WHERE ID = '"+id+"';";
                break;
            case "SYCS":
                search = "SELECT FOA, ADV_JAVA, CN, SE, LA, DOTNET, ANDROID FROM SYSTUDENTS WHERE ID = '"+id+"';";
                break;
            case "TYCS":
                search = "SELECT DATA_SCIENCE, CLOUD_COMPUT, INFO_RETRIEVAL, ETHICAL_HACKING, CYBER_FORENSIC FROM TYSTUDENTS WHERE ID = '"+id+"';";
                break;
        }
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(search, null);
        if(cursor.moveToFirst()){
            float[] a;
            float overall;
            String set;
            switch (aClass) {
                case "FYCS":
                    a = new float[7];
                    for (int i = 0; i < a.length; i++) {
                        a[i] = cursor.getFloat(i);
                    }
                    overall = 0f;
                    for (float v : a) {
                        overall += v;
                    }
                    overall /= a.length;
                    set = "Update FYSTUDENTS Set OVERALL = " + overall + " Where ID = '" + id + "';";
                    db.execSQL(set);
                    break;
                case "SYCS":
                    a = new float[7];
                    for (int i = 0; i < a.length; i++) {
                        a[i] = cursor.getFloat(i);
                    }
                    overall = 0f;
                    for (float v : a) {
                        overall += v;
                    }
                    overall /= a.length;
                    set = "Update SYSTUDENTS Set OVERALL = " + overall + " Where ID = '" + id + "';";
                    db.execSQL(set);
                    break;
                case "TYCS":
                    a = new float[5];
                    for (int i = 0; i < a.length; i++) {
                        a[i] = cursor.getFloat(i);
                    }
                    overall = 0f;
                    for (float v : a) {
                        overall += v;
                    }
                    overall /= a.length;
                    set = "Update SYSTUDENTS Set OVERALL = " + overall + " Where ID = '" + id + "';";
                    db.execSQL(set);
                    break;
            }
        }
    }
}
