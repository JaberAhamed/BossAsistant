package com.example.jahed.bossassistant;


import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class FileSetActivity extends AppCompatActivity {


    String getKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_set);

        getKey=getIntent().getStringExtra("key");
        int getPos=getIntent().getIntExtra("pos",0);

        Toast.makeText(this, getKey+" "+getPos, Toast.LENGTH_SHORT).show();
        if (getKey==null){

            chooseFragment();
        }
        else if (getKey.equals("MGS")){


            android.support.v4.app.FragmentManager fm=getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction ft=fm.beginTransaction();
            Bundle bundle = new Bundle();
            bundle.putString("pos", String.valueOf(getPos));
            MessageFragment fragment=new MessageFragment();
            fragment.setArguments(bundle);
            ft.add(R.id.fragmentContainer,fragment);
            ft.commit();

        }
        else if (getKey.equals("EML")){

            android.support.v4.app.FragmentManager fm=getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction ft=fm.beginTransaction();
            Bundle bundle = new Bundle();
            bundle.putInt("pos", getPos);
            EmailFragment fragment=new EmailFragment();
            fragment.setArguments(bundle);
            ft.add(R.id.fragmentContainer,fragment);
            ft.commit();

        }


    }

    private void chooseFragment() {



            android.support.v4.app.FragmentManager fm=getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction ft=fm.beginTransaction();
            Fragment fragment=new Fragment();
            ft.add(R.id.fragmentContainer,fragment);
            ft.commit();




    }





    public void chageFragment(View view) {
        Fragment fragment=null;

        switch (view.getId()){
            case R.id.messageTV :{


                fragment=new MessageFragment();

                break;
            }
            case R.id.emailTV:{

                fragment=new EmailFragment();

                break;

            }


        }
        android.support.v4.app.FragmentManager fm=getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.fragmentContainer,fragment);
        ft.commit();


    }
}
