package com.example.jahed.bossassistant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class EmailDataManager {
    private Context context;

    private AppDataHelper appDataHelper;
    private SQLiteDatabase sqLiteDatabase;

    public EmailDataManager(Context context) {
        this.context = context;
        appDataHelper=new AppDataHelper(context);
    }

    public long addEmail(EmailClass emailClass){
        open();
        ContentValues contentValues=new ContentValues();
        contentValues.put(AppDataHelper.COLUMN_EMAIL_NAME,emailClass.getEmailName());

        contentValues.put(AppDataHelper.COLUMN_EML_YEAR,emailClass.getEmYear());
        contentValues.put(AppDataHelper.COLUMN_EML_MONTH,emailClass.getEmMonth());
        contentValues.put(AppDataHelper.COLUMN_EML_DAY,emailClass.getEmDayOfMonth());
        contentValues.put(AppDataHelper.COLUMN_EML_HOURE,emailClass.getEmHoure());
        contentValues.put(AppDataHelper.COLUMN_EML_MINUTE,emailClass.getEmMinute());

        contentValues.put(AppDataHelper.COLUMN_EMAIL_TO,emailClass.getEmailTo());
        contentValues.put(AppDataHelper.COLUMN_EMAIL_SUBJECT,emailClass.getSubject());
        contentValues.put(AppDataHelper.COLUMN_EMAIL_TEXT,emailClass.getEmailText());
        contentValues.put(AppDataHelper.COLUMN_EMAIL_ATTACH_FILE,emailClass.getFileImgPath());





        long insertedRow=sqLiteDatabase.insert(AppDataHelper.TABLE_EMAIL,null,contentValues);
        sqLiteDatabase.close();
        return insertedRow;
    }
    public long updateEmail(EmailClass emailClass){
        open();
        ContentValues contentValues=new ContentValues();
        contentValues.put(AppDataHelper.COLUMN_EMAIL_NAME,emailClass.getEmailName());

        contentValues.put(AppDataHelper.COLUMN_EML_YEAR,emailClass.getEmYear());
        contentValues.put(AppDataHelper.COLUMN_EML_MONTH,emailClass.getEmMonth());
        contentValues.put(AppDataHelper.COLUMN_EML_DAY,emailClass.getEmDayOfMonth());
        contentValues.put(AppDataHelper.COLUMN_EML_HOURE,emailClass.getEmHoure());
        contentValues.put(AppDataHelper.COLUMN_EML_MINUTE,emailClass.getEmMinute());

        contentValues.put(AppDataHelper.COLUMN_EMAIL_TO,emailClass.getEmailTo());
        contentValues.put(AppDataHelper.COLUMN_EMAIL_SUBJECT,emailClass.getSubject());
        contentValues.put(AppDataHelper.COLUMN_EMAIL_TEXT,emailClass.getEmailText());
        contentValues.put(AppDataHelper.COLUMN_EMAIL_ATTACH_FILE,emailClass.getFileImgPath());

        long insertedRow=sqLiteDatabase.update(AppDataHelper.TABLE_EMAIL,contentValues,AppDataHelper.COLUMN_EMAIL_ID+" = "+emailClass.getId(),null);
        sqLiteDatabase.close();
        return insertedRow;

    }

    public long deleteEmail(int id){
        open();
        long deleted=sqLiteDatabase.delete(AppDataHelper.TABLE_EMAIL,AppDataHelper.COLUMN_EMAIL_ID+" = "+id,null);
        sqLiteDatabase.close();
        return deleted;
    }
    public ArrayList<EmailClass> getAllEmail(){
        ArrayList<EmailClass>emailClasses=new ArrayList<>();
        open();
        String selectQuery="select * from "+AppDataHelper.TABLE_EMAIL;
        Cursor cursor=sqLiteDatabase.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                 EmailClass emailClass=new EmailClass();
                emailClass.setId(cursor.getInt(cursor.getColumnIndex(AppDataHelper.COLUMN_EMAIL_ID)));
                emailClass.setEmailName(cursor.getString(cursor.getColumnIndex(AppDataHelper.COLUMN_EMAIL_NAME)));
                emailClass.setEmYear(cursor.getInt(cursor.getColumnIndex(AppDataHelper.COLUMN_EML_YEAR)));
                emailClass.setEmMonth(cursor.getInt(cursor.getColumnIndex(AppDataHelper.COLUMN_EML_MONTH)));
                emailClass.setEmDayOfMonth(cursor.getInt(cursor.getColumnIndex(AppDataHelper.COLUMN_EML_DAY)));
                emailClass.setEmHoure(cursor.getInt(cursor.getColumnIndex(AppDataHelper.COLUMN_EML_HOURE)));
                emailClass.setEmMinute(cursor.getInt(cursor.getColumnIndex(AppDataHelper.COLUMN_EML_MINUTE)));



                emailClass.setEmailTo(cursor.getString(cursor.getColumnIndex(AppDataHelper.COLUMN_EMAIL_TO)));
                emailClass.setSubject(cursor.getString(cursor.getColumnIndex(AppDataHelper.COLUMN_EMAIL_SUBJECT)));
                emailClass.setEmailText(cursor.getString(cursor.getColumnIndex(AppDataHelper.COLUMN_EMAIL_TEXT)));
                emailClass.setFileImgPath(cursor.getString(cursor.getColumnIndex(AppDataHelper.COLUMN_EMAIL_ATTACH_FILE)));

                emailClasses.add(emailClass);
            }while(cursor.moveToNext());
        }
        return emailClasses;
    }

    private void open() {

        sqLiteDatabase=appDataHelper.getWritableDatabase();
    }
}
