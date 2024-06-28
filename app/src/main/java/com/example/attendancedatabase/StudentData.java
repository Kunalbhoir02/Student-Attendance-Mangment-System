package com.example.attendancedatabase;

public class StudentData {
    public String ID;
    public String sName;
    public int rollNo;
    public String aClass;
    public String div;
    public String date;
    public boolean present = false;
    public String attend;
    public String addedBy;

    public StudentData(String ID, String sName, int rollNo, String aClass, String div, String addedBy) {
        this.ID = ID;
        this.sName = sName;
        this.rollNo = rollNo;
        this.aClass = aClass;
        this.div = div;
        this.addedBy = addedBy;
    }

    public StudentData(String ID, String date, String attend) {
        this.ID = ID;
        this.date = date;
        this.attend = attend;
    }
}
