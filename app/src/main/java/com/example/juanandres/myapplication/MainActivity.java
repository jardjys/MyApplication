package com.example.juanandres.myapplication;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements Friends.OnFragmentInteractionListener, Hobbies.OnFragmentInteractionListener{

    private boolean hobbyflag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hobbyflag = false;
    }

    public void setFriends(View view){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        Friends f = Friends.newInstance("friendfragment");

        f.addFragmentListener(this);
        transaction.add(R.id.fragmentContainer, f, "friendfragment");
        transaction.commit();
    }

    public void setHobby(View v){
        if(!hobbyflag) {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();

            Hobbies ef = Hobbies.newInstance("text label", "button");
            transaction.add(R.id.fragmentContainer, ef, "hobby");
            transaction.commit();
            hobbyflag = true;
        } else{
            FragmentManager fm = getFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            Fragment f = fm.findFragmentByTag("hobby");

            transaction.remove(f);
            transaction.commit();
            hobbyflag = false;
        }
    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
