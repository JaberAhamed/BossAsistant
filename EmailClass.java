package com.example.jahed.bossassistant;

/**
 * Created by jahed on 10/2/2018.
 */

public class EmailClass {

    private  int id;
    private  String emailName;
    private  int    emYear;
    private  int    emMonth;
    private  int    emDayOfMonth;
    private  int    emHoure;
    private  int    emMinute;
    private  String emailTo;
    private  String subject;
    private  String emailText;
    private  String fileImgPath;



    private boolean isSelected=false;

    public EmailClass() {
    }

    public EmailClass(int id, String emailName, int emYear, int emMonth, int emDayOfMonth, int emHoure, int emMinute, String emailTo, String subject, String emailText,String fileImgPath) {
        this.id = id;
        this.emailName = emailName;
        this.emYear = emYear;
        this.emMonth = emMonth;
        this.emDayOfMonth = emDayOfMonth;
        this.emHoure = emHoure;
        this.emMinute = emMinute;
        this.emailTo = emailTo;
        this.subject = subject;
        this.emailText = emailText;
        this.fileImgPath=fileImgPath;
    }

    public EmailClass(String emailName, int emYear, int emMonth, int emDayOfMonth, int emHoure, int emMinute, String emailTo, String subject, String emailText,String fileImgPath) {
        this.emailName = emailName;
        this.emYear = emYear;
        this.emMonth = emMonth;
        this.emDayOfMonth = emDayOfMonth;
        this.emHoure = emHoure;
        this.emMinute = emMinute;
        this.emailTo = emailTo;
        this.subject = subject;
        this.emailText = emailText;
        this.fileImgPath=fileImgPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmailName() {
        return emailName;
    }

    public void setEmailName(String emailName) {
        this.emailName = emailName;
    }

    public int getEmYear() {
        return emYear;
    }

    public void setEmYear(int emYear) {
        this.emYear = emYear;
    }

    public int getEmMonth() {
        return emMonth;
    }

    public void setEmMonth(int emMonth) {
        this.emMonth = emMonth;
    }

    public int getEmDayOfMonth() {
        return emDayOfMonth;
    }

    public void setEmDayOfMonth(int emDayOfMonth) {
        this.emDayOfMonth = emDayOfMonth;
    }

    public int getEmHoure() {
        return emHoure;
    }

    public void setEmHoure(int emHoure) {
        this.emHoure = emHoure;
    }

    public int getEmMinute() {
        return emMinute;
    }

    public void setEmMinute(int emMinute) {
        this.emMinute = emMinute;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEmailText() {
        return emailText;
    }

    public void setEmailText(String emailText) {
        this.emailText = emailText;
    }

    public boolean getSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
    public String getFileImgPath() {
        return fileImgPath;
    }

    public void setFileImgPath(String fileImgPath) {
        this.fileImgPath = fileImgPath;
    }
}

