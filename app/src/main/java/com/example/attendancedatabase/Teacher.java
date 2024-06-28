package com.example.attendancedatabase;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Teacher {
    ArrayList<StudentData> sd;
    public String tName;
    public String[] tSubject;
    Context context;
    public Teacher(Context context){this.context = context;}
    public boolean login(String name, String pass){
        SQLiteDatabase db = new DatabaseHelper(context).getReadableDatabase();
        tName = name;
        String search = "SELECT NAME FROM TEACHERS;";
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(search, null);
        if(cursor.moveToFirst()){
            do {
                String teachName = cursor.getString(0);
                if(teachName.equals(tName)){
                    cursor.close();
                    db.close();
                    return pass.equals("12345678");
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return false;
    }

    public String[] gettSubject(String name){
        SQLiteDatabase db = new DatabaseHelper(context).getReadableDatabase();
        String search = "SELECT * FROM TEACHERS WHERE NAME = '"+ name + "' ;";
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(search, null);
        if (cursor.moveToFirst()) {
            String subjects = cursor.getString(1);
            tSubject = subjects.split(", ");
        }
        cursor.close();
        db.close();
        return tSubject;
    }

    public void addStudent(String Id, String name, String aClass, String div, int rollNo, String addedBy){
        SQLiteDatabase db = new DatabaseHelper(context).getWritableDatabase();
        String addToStudents = null;
        switch (aClass) {
            case "FYCS":
                addToStudents = "INSERT INTO FYSTUDENTS VALUES('" + Id + "','" + name + "','" + aClass + "','" + div + "'," + rollNo + ",'" + addedBy + "',0,0,0,0,0,0,0,0);";
                break;
            case "SYCS":
                addToStudents = "INSERT INTO SYSTUDENTS VALUES('" + Id + "','" + name + "','" + aClass + "','" + div + "'," + rollNo + ",'" + addedBy + "',0,0,0,0,0,0,0,0);";
                break;
            case "TYCS":
                addToStudents = "INSERT INTO TYSTUDENTS VALUES('" + Id + "','" + name + "','" + aClass + "','" + div + "'," + rollNo + ",'" + addedBy + "',0,0,0,0,0,0,0,0);";
                break;
        }
        db.execSQL(addToStudents);
        db.close();
    }

    public void addTeacher(String name, String subjects, String addedBy){
        SQLiteDatabase db = new DatabaseHelper(context).getWritableDatabase();
        String addToTeachers = "INSERT INTO TEACHERS VALUES('" + name + "', '" + subjects + "', '" + addedBy + "');";
        db.execSQL(addToTeachers);
        db.close();
    }

    @SuppressLint("Recycle")
    public ArrayList<StudentData> getStudentData(String aClass, String div){
        sd = new ArrayList<>();
        SQLiteDatabase db = new DatabaseHelper(context).getReadableDatabase();
        String search = null;
        switch (aClass) {
            case "FYCS":
                search = "SELECT ID, NAME, CLASS, DIV, ROLLNO, ADDED_BY FROM FYSTUDENTS WHERE CLASS = '" + aClass + "' AND DIV = '" + div + "' ORDER BY ROLLNO";
                break;
            case "SYCS":
                search = "SELECT ID, NAME, CLASS, DIV, ROLLNO, ADDED_BY FROM SYSTUDENTS WHERE CLASS = '" + aClass + "' AND DIV = '" + div + "' ORDER BY ROLLNO";
                break;
            case "TYCS":
                search = "SELECT ID, NAME, CLASS, DIV, ROLLNO, ADDED_BY FROM TYSTUDENTS WHERE CLASS = '" + aClass + "' AND DIV = '" + div + "' ORDER BY ROLLNO";
                break;
        }
        Cursor cursor = db.rawQuery(search, null);
        cursor.moveToFirst();
        do {
            String id = cursor.getString(0);
            String name = cursor.getString(1);
            int rollNo = cursor.getInt(4);
            String addedBy = cursor.getString(5);
            sd.add(new StudentData(id,name,rollNo,aClass,div,addedBy));
        } while (cursor.moveToNext());
        cursor.close();
        db.close();
        return sd;
    }

    public void addAttendance(String subject, String id, boolean present, String addedBy, String aClass){
        SQLiteDatabase db = new DatabaseHelper(context).getReadableDatabase();
        String addattend = "INSERT INTO " + subject + " VALUES('" + id + "','" + present + "', (SELECT DATE())," +
                "(select case strftime('%m', date('now')) when '01' then 'January' when '02' then 'February' when '03' then 'March' when '04' then 'April' when '05' then 'May' when '06' then 'June' when '07' then 'July' when '08' then 'August' when '09' then 'September' when '10' then 'October' when '11' then 'November' when '12' then 'December' else '' end), " +
                "(SELECT TIME()),'"+addedBy+"' )";
        db.execSQL(addattend);
        String search1 = "SELECT ID FROM " +subject+" WHERE ID = '" +id+"' AND MONTH = 'February';";
        ArrayList<String> total = new ArrayList<>();
        Cursor cursor = db.rawQuery(search1, null);
        cursor.moveToFirst();
        do {
            total.add(cursor.getString(0));
        } while (cursor.moveToNext());
        cursor.close();
        String search2 = "SELECT ID FROM " +subject+" WHERE ID = '" +id+"' AND PRESENT = '"+present+"' AND MONTH = 'February';";
        ArrayList<String> attending = new ArrayList<>();
        cursor = db.rawQuery(search2, null);
        cursor.moveToFirst();
        do {
            attending.add(cursor.getString(0));
        } while (cursor.moveToNext());
        cursor.close();
        float a = ((float) attending.size() /total.size())*100;
        String addattendpre = null;
        switch (aClass) {
            case "FYCS":
                addattendpre = "Update FYSTUDENTS Set "+subject+" = "+a+" Where ID = '" + id + "';";
                break;
            case "SYCS":
                addattendpre = "Update SYSTUDENTS Set "+subject+" = "+a+" Where ID = '" + id + "';";
                break;
            case "TYCS":
                addattendpre = "Update TYSTUDENTS Set "+subject+" = "+a+" Where ID = '" + id + "';";
                break;
        }
        db.execSQL(addattendpre);
        db.close();
    }
}
