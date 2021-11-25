package com.example.quizapp_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
  int index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        QuestionBank qbank = new QuestionBank();

        updateFragmet(qbank.questionslist.get(index).getQuestion(),qbank.colorlist.get(index));

    }
    private void updateFragmet(int Questid,int colorid){
        FragmentManager manager = getSupportFragmentManager();
        manager.findFragmentById(R.id.mainframelayout);//Connected
        myFragment myfragobj = myFragment.newInstance(Questid,colorid);
        //layout is connecting with the fragmentclass(name of the frameLayout name) using the fragment object
        manager.beginTransaction().add(R.id.mainframelayout,myfragobj).commit();

    }
}