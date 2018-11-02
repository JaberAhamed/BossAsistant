package com.example.jahed.bossassistant;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {

    final private int REQUEST_MULTIPLE_PERMISSIONS = 124;
    private static final int PERMISSION_REQUEST_CODE = 200;
    RecyclerView recyclerViewMessage,recyclerViewEmail;
    MessageDataManager messageDataManager;
    EmailDataManager   emailDataManager;
    ArrayList<MessageClass>messageClasses;
    ArrayList<EmailClass>emailClasses;
    ArrayList<Object>objects=new ArrayList<>();
  //  Button button;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         messageDataManager=new MessageDataManager(this);
         emailDataManager=new EmailDataManager(this);
        messageClasses=messageDataManager.getAllMessage();
        emailClasses=emailDataManager.getAllEmail();
       // button=findViewById(R.id.sentId);


        recyclerViewMessage =findViewById(R.id.recyclerViewMessage);
        recyclerViewEmail=findViewById(R.id.recyclerViewEmail);



          objects.addAll(messageClasses);
          objects.addAll(emailClasses);


        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewMessage.setLayoutManager(linearLayoutManager);
        recyclerViewMessage.setAdapter(new AppDataAdapter(this,messageClasses));

        LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(this);
        linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewEmail.setLayoutManager(linearLayoutManager1);
        recyclerViewEmail.setAdapter(new AppEmailDataAdapter(this,emailClasses));


       /* recyclerViewMessage.addItemDecoration(new ItemOffsetDecoration(10));
        recyclerViewEmail.addItemDecoration(new ItemOffsetDecoration(10));*/

       /* button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number="12524";
                String sms="dfhdjhdso";
                try {
                    SmsManager smsManager =SmsManager.getDefault();
                    smsManager.sendTextMessage(number,null,sms,null,null);
                    Toast.makeText(MainActivity.this, "setnd", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_SHORT).show();
                }
            }
        });*/

        if (!checkPermission()) {
            openActivity();
        } else {
            if (checkPermission()) {
                requestPermissionAndContinue();
            } else {
                openActivity();
            }
        }
        AccessSms();
        AccessContact();


    }




    @RequiresApi(api = Build.VERSION_CODES.M)
    private void AccessContact() {
        List<String> permissionsNeeded = new ArrayList<String>();
        final List<String> permissionsList = new ArrayList<String>();
        if (!addPermission(permissionsList, Manifest.permission.READ_CONTACTS))
            permissionsNeeded.add("Read Contacts");
        if (!addPermission(permissionsList, Manifest.permission.WRITE_CONTACTS))
            permissionsNeeded.add("Write Contacts");
        if (permissionsList.size() > 0) {
            if (permissionsNeeded.size() > 0) {
                String message = "You need to grant access to " + permissionsNeeded.get(0);
                for (int i = 1; i < permissionsNeeded.size(); i++)
                    message = message + ", " + permissionsNeeded.get(i);
                showMessageOKCancel(message,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                                        REQUEST_MULTIPLE_PERMISSIONS);
                            }
                        });
                return;
            }
            requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                    REQUEST_MULTIPLE_PERMISSIONS);
            return;
        }

    }




    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean addPermission(List<String> permissionsList, String permission) {
        if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
            permissionsList.add(permission);

            if (!shouldShowRequestPermissionRationale(permission))
                return false;
        }
        return true;
    }


    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }
    private void AccessSms() {
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.SEND_SMS)){

                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.SEND_SMS},1);
            }
            else {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.SEND_SMS},1);

            }

        }
        else {

        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode){

            case 1:{
                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    if (ContextCompat.checkSelfPermission(MainActivity.this,
                            Manifest.permission.SEND_SMS)==PackageManager.PERMISSION_GRANTED){

                        Toast.makeText(this, "Permission Granted ", Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    Toast.makeText(this, "No pervission ", Toast.LENGTH_SHORT).show();
                }
            }

            case 2:{

                if (requestCode == PERMISSION_REQUEST_CODE) {
                    if (permissions.length > 0 && grantResults.length > 0) {

                        boolean flag = true;
                        for (int i = 0; i < grantResults.length; i++) {
                            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                                flag = false;
                            }
                        }
                        if (flag) {
                            openActivity();
                        } else {
                            finish();
                        }

                    } else {
                        finish();
                    }
                } else {
                    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                }
            }
            return;

        }

    }

    public void fileActivity(View view) {
        Intent intent=new Intent(this,FileSetActivity.class);
        startActivity(intent);
    }


    private boolean checkPermission() {

        return ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissionAndContinue() {
        if (ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, WRITE_EXTERNAL_STORAGE)
                    && ActivityCompat.shouldShowRequestPermissionRationale(this, READ_EXTERNAL_STORAGE)) {
                android.app.AlertDialog.Builder alertBuilder = new android.app.AlertDialog.Builder(this);
                alertBuilder.setCancelable(true);
                alertBuilder.setTitle("permission");
                alertBuilder.setMessage("needpermission");
                alertBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{WRITE_EXTERNAL_STORAGE
                                , READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
                    }
                });
                android.app.AlertDialog alert = alertBuilder.create();
                alert.show();
                Log.e("", "permission denied, show dialog");
            } else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{WRITE_EXTERNAL_STORAGE,
                        READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
            }
        } else {
            openActivity();
        }
    }


    private void openActivity() {
        //add your further process after giving permission or to download images from remote server.
    }



}
