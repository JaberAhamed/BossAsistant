package com.example.jahed.bossassistant;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jahed on 10/2/2018.
 */

public class AppDataHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME="AppallDataDB";

    private static final int DATABASE_VERSION=1;

    public static final String TABLE_MESSAGE="messageTable";
    public static final String COLUMN_MESSAGE_ID="mgs_id";
    public static final String COLUMN_MESSAGE_NAME="mgs_Name";
    public static final String COLUMN_MGS_YEAR="year";
    public static final String COLUMN_MGS_MONTH="month";
    public static final String COLUMN_MGS_DAY="day";
    public static final String COLUMN_MGS_HOURE="houre";
    public static final String COLUMN_MGS_MINUTE="minute";
    public static final String COLUMN_MESSAGE_NUMBER="mgs_Number";
    public static final String COLUMN_MESSAGE_TEXT="mgs_Text";


    public static final String TABLE_EMAIL="emil_Table";
    public static final String COLUMN_EMAIL_ID="email_id";
    public static final String COLUMN_EMAIL_NAME="email_Name";
    public static final String COLUMN_EML_YEAR="year";
    public static final String COLUMN_EML_MONTH="month";
    public static final String COLUMN_EML_DAY="day";
    public static final String COLUMN_EML_HOURE="houre";
    public static final String COLUMN_EML_MINUTE="minute";
    public static final String COLUMN_EMAIL_TO="email_To";
    public static final String COLUMN_EMAIL_SUBJECT="email_Number";
    public static final String COLUMN_EMAIL_TEXT="email_Text";
    public static final String COLUMN_EMAIL_ATTACH_FILE="email_AttachFile";



    public static final String TABLE_MGS_EML_TOG_POSITION="toggoleButonPostionTable";
    public static final String COLUMN_ToggolButtonHandle_ID="id";
    public static final String COLUMN_TogglButtonEmMgName="Em_Mg";
    public static final String COLUMN_ToggolButtonHandle_position="position";



    public  static final String UserInfoTable="userInfoTable";
    public static final String COLUMN_USEREMAIL_ID="id";
    public static final String COLUMN_USER_EMAIL="user_email";
    public static final String COLUMN_USER_PASSWORD="user_pass";



    private String createMessageTable="create table "+TABLE_MESSAGE+"("+COLUMN_MESSAGE_ID+" integer primary key autoincrement,"+COLUMN_MESSAGE_NAME+" text,"+COLUMN_MGS_YEAR+" integer,"+
            COLUMN_MGS_MONTH+" integer,"+COLUMN_MGS_DAY+" integer,"+ COLUMN_MGS_HOURE+" integer,"+COLUMN_MGS_MINUTE+" integer,"+COLUMN_MESSAGE_NUMBER+" text,"+COLUMN_MESSAGE_TEXT+" text);";


    private String createEmailTable="create table "+TABLE_EMAIL+"("+COLUMN_EMAIL_ID+" integer primary key autoincrement,"+COLUMN_EMAIL_NAME+" text,"+
            COLUMN_EML_YEAR+" integer,"+COLUMN_EML_MONTH+" integer,"+COLUMN_EML_DAY+" integer,"+
            COLUMN_EML_HOURE+" integer,"+COLUMN_EML_MINUTE+" integer,"+COLUMN_EMAIL_TO+" text,"+COLUMN_EMAIL_SUBJECT+" text,"+COLUMN_EMAIL_TEXT+" text,"+COLUMN_EMAIL_ATTACH_FILE+" text);";

    private String createToggolButtonPosotion="create table "+TABLE_MGS_EML_TOG_POSITION+"("+COLUMN_ToggolButtonHandle_ID+" integer primary key autoincrement,"+COLUMN_TogglButtonEmMgName+" text,"+COLUMN_ToggolButtonHandle_position+" integer );";


    private String createUserInfo="create table "+UserInfoTable+"("+COLUMN_USEREMAIL_ID+" integer primary key autoincrement,"+COLUMN_USER_EMAIL+" text,"+COLUMN_USER_PASSWORD+" text );";


    public AppDataHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createMessageTable);
        db.execSQL(createEmailTable);
        db.execSQL(createToggolButtonPosotion);
        db.execSQL(createUserInfo);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
