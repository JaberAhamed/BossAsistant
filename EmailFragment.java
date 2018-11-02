package com.example.jahed.bossassistant;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class EmailFragment extends Fragment {

    static final int PICK_CONTACT = 1;
    Button dateBtn,timeBtn,saveData,selectFileBtn;
    private int year, month, day, hour, minute;
    int yearr, motTh, dayofmonth, houre, minutt, h, m;
    private Calendar now;
    int getPos;
    private String filename;
    private static final int REQUEST_CHOOSER = 1234;
    int index;
    String newDate,hour_String,minute_String,format;
    EditText emailET,subjectEt,textET,userEmail,userPassword;
    EmailDataManager emailDataManager;
    ArrayList<EmailClass>emailClasses;
    public EmailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final Bundle bundle = getArguments();

        if (bundle!=null){
            getPos = getArguments().getInt("pos",0);
            Toast.makeText(getContext(), String.valueOf(getPos), Toast.LENGTH_SHORT).show();

        }

         View v=inflater.inflate(R.layout.fragment_email, container, false);
        now = Calendar.getInstance();
        year = now.get(Calendar.YEAR);
        month = now.get(Calendar.MONTH);
        day = now.get(Calendar.DAY_OF_MONTH);

        hour = now.get(Calendar.HOUR_OF_DAY);
        minute = now.get(Calendar.MINUTE);
        dateBtn=v.findViewById(R.id.selectDateEmialET);
        timeBtn=v.findViewById(R.id.selectTimeEmailET);
        saveData=v.findViewById(R.id.saveEmailBtn);
        emailET =v.findViewById(R.id.writeEmailET);
        subjectEt=v.findViewById(R.id.emailSubjectET);
        textET=v.findViewById(R.id.emailTextET);
        selectFileBtn=v.findViewById(R.id.selectFileBtn);
        userEmail=v.findViewById(R.id.user_email);
        userPassword=v.findViewById(R.id.user_password);


        emailDataManager =new EmailDataManager(getContext());



        if (bundle!=null){
            Toast.makeText(getContext(), "bundle", Toast.LENGTH_SHORT).show();
             emailClasses=new ArrayList<>();
            emailClasses=emailDataManager.getAllEmail();


            yearr=emailClasses.get(Integer.valueOf(getPos)).getEmYear();
            motTh=emailClasses.get(Integer.valueOf(getPos)).getEmMonth();
            dayofmonth=emailClasses.get(Integer.valueOf(getPos)).getEmDayOfMonth();
            houre=emailClasses.get(Integer.valueOf(getPos)).getEmHoure();
            minutt=emailClasses.get(Integer.valueOf(getPos)).getEmMinute();

            int getmonth=motTh+1;
            dateBtn.setText(yearr+"/"+getmonth+"/"+dayofmonth);
            setTime(houre,minutt);

            emailET.setText(emailClasses.get(getPos).getEmailTo());
            subjectEt.setText(emailClasses.get(getPos).getSubject());
            textET.setText(emailClasses.get(getPos).getEmailText());
            filename=emailClasses.get(getPos).getFileImgPath();

        }



        emailET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, PICK_CONTACT);
            }
        });


        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        dateListener, year, month, day);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();



            }


            DatePickerDialog.OnDateSetListener dateListener =
                    new DatePickerDialog.OnDateSetListener() {
                        View view;

                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {


                            now.set(year, month, dayOfMonth);

                            yearr = datePicker.getYear();
                            motTh = datePicker.getMonth();
                            dayofmonth = datePicker.getDayOfMonth();
                            // Toast.makeText(context, "   month value -:   "+month, Toast.LENGTH_SHORT).show();


                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                            newDate = simpleDateFormat.format(now.getTime());

                            dateBtn.setText(newDate);




                            Toast.makeText(getContext(), "   month value -:   "+month, Toast.LENGTH_SHORT).show();
                        }


                    };

        });


        timeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), timeListener,  now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), false);


                timePickerDialog.show();

            }


            private TimePickerDialog.OnTimeSetListener timeListener =
                    new TimePickerDialog.OnTimeSetListener() {


                        @RequiresApi(api = Build.VERSION_CODES.M)
                        @Override
                        public void onTimeSet(TimePicker timePicker, int i, int i1) {
                            now.set(0, 0, 0, i, i1);

                            timePicker.setCurrentHour(now.get(Calendar.HOUR_OF_DAY));
                            timePicker.setCurrentMinute(now.get(Calendar.MINUTE));


                            houre = timePicker.getHour();
                            minutt = timePicker.getMinute();
                            setTime(houre,minutt);


                        }
                    };

        });



        saveData.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {

                String email_to= emailET.getText().toString();
                String emailText=textET.getText().toString();
                String emailSub=subjectEt.getText().toString();

                if(yearr==0||motTh==0||dayofmonth==0||houre==0||minutt==0||email_to==null||emailSub==null||emailText==null){

                    Toast.makeText(getActivity(), "Any field empty is not allow"+email_to+" "+emailText+" "+emailText, Toast.LENGTH_SHORT).show();
                }
                else {




                    if (bundle!=null){

                       Toast.makeText(getContext(), "update"+getPos , Toast.LENGTH_SHORT).show();
                        long data= emailDataManager.updateEmail(new EmailClass(emailClasses.get(Integer.valueOf(getPos)).getId(),"Email",yearr,motTh,dayofmonth,houre,minutt,email_to,emailSub,emailText,filename));
                        if (data>0){
                            Toast.makeText(getActivity(), "updateold ", Toast.LENGTH_SHORT).show();
                        }
                        Intent intent=new Intent(getContext(),MainActivity.class);
                        startActivity(intent);

                    }
                    else {

                        Toast.makeText(getContext(), "save", Toast.LENGTH_SHORT).show();
                        long data= emailDataManager.addEmail(new EmailClass("Email",yearr,motTh,dayofmonth,houre,minutt,email_to,emailSub,emailText,filename));
                        if (data>0){
                            Toast.makeText(getActivity(), "save", Toast.LENGTH_SHORT).show();

                        }

                        UserInfoDataManage userInfoDataManage=new UserInfoDataManage(getContext());

                        ArrayList<UserEmailInfo>userEmailInfos=new ArrayList<>();

                        userEmailInfos=userInfoDataManage.getUserInfo();
                        if (userEmailInfos.size()<1){
                            String userMail=userEmail.getText().toString();
                            String userPass=userPassword.getText().toString();
                            Toast.makeText(getContext(), String.valueOf(userEmailInfos.size()), Toast.LENGTH_SHORT).show();

                            if (userMail==null||userPass==null){

                             userEmail.setBackgroundColor(R.color.colorAccent);
                             userPassword.setBackgroundColor(R.color.colorAccent);

                            }
                            else {

                                long dfdf=userInfoDataManage.addUserEmail(new UserEmailInfo(userMail,userPass));
                                if (dfdf>0){
                                    Toast.makeText(getContext(), "save the user email", Toast.LENGTH_SHORT).show();
                                }
                            }


                        }
                        Intent intent=new Intent(getContext(),MainActivity.class);
                        startActivity(intent);

                    }










                }


            }
        });


        selectFileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent target = FileUtile.createGetContentIntent();

                Intent intent = Intent.createChooser(target, "chhosefile ");
                try {
                    startActivityForResult(intent, REQUEST_CHOOSER);
                } catch (ActivityNotFoundException e) {
                    // The reason for the existence of aFileChooser
                }
            }
        });



        return v;
    }

    private void setTime(int hou, int min) {

        h = hou;
        m = min;

        if (h == 0) {

            h += 12;

            format = "AM";
        } else if (h == 12) {

            format = "PM";

        } else if (h > 12) {

            h -= 12;

            format = "PM";

        } else {

            format = "AM";
        }


        hour_String = String.valueOf(h);
        minute_String = String.valueOf(m);
        if (h < 10) {
            hour_String = "0" + hour_String;
        }
        if (m < 10) {
            minute_String = "0" + minute_String;
        }


        timeBtn.setText(hour_String + " : " + minute_String + " " + format);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        switch (reqCode) {
            case (PICK_CONTACT):
                if (resultCode == RESULT_OK) {
                    Uri contactData = data.getData();
                    Cursor c =getActivity().managedQuery(contactData,null,null,null,null) ;


                    if (c.moveToFirst()) {
                        String id = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
                        String hasPhone = c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                        try {
                            if (hasPhone.equalsIgnoreCase("1")) {


                                Cursor emailCursor = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null,
                                        ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = " + id,
                                        null, null);
                                emailCursor.moveToFirst();
                                String cEmail = emailCursor.getString(emailCursor.getColumnIndex("data1"));

                                emailET.setText(cEmail);

                            }

                        }
                        catch (Exception ex)
                        {
                            ex.getMessage();
                        }
                    }
                }
                break;
            case REQUEST_CHOOSER:
                if (resultCode == RESULT_OK) {
                    final Uri uri = data.getData();

                    // Get the File path from the Uri
                    filename = FileUtile.getPath(getContext(),uri);



                    String file=filename.substring(filename.lastIndexOf("/")+1);
                    Toast.makeText(getContext(),filename , Toast.LENGTH_SHORT).show();
                    selectFileBtn.setText(file);


                }
                break;
        }
    }






}
