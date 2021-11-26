package com.example.quizapp_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    Button trueButton, falseButton;
    ProgressBar progressBar;
    int index = 0;
    int falseclicks =0;
    int trueclicks = 0;
    int correctAns = 0;
    int progress = 0;
    QuestionBank qbank;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        trueButton = findViewById(R.id.button);
        falseButton = findViewById(R.id.button2);
        progressBar = findViewById(R.id.progressBar);
        qbank = new QuestionBank();
        Collections.shuffle(qbank.colorlist); //Shuffle Color
        updateFragment(qbank.questionslist.get(index).getQuestion(),qbank.getColorlist().get(index));
        //True Button
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(qbank.questionslist.get(index).getAnswer()== true) {
                    correctAns++;
                    Toast.makeText(MainActivity.this, "Your Answer is Correct ", Toast.LENGTH_SHORT).show();
                }
                else { Toast.makeText(MainActivity.this, "Your Answer is Wrong ", Toast.LENGTH_SHORT).show();}
                index++;
               updateFragment(qbank.questionslist.get(index).getQuestion(),qbank.getColorlist().get(index));
                progress = progress+10;
                progressBar.setProgress(progress);
                progressBar.setMax(60);
            }
        });
        //False Button
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(qbank.questionslist.get(index).getAnswer()== false)
        {correctAns++;
            Toast.makeText(MainActivity.this, "Your Answer is Correct ", Toast.LENGTH_LONG).show();
           //System.out.println("(InsideFalse)no of correct : "+correctAns);
        }else{Toast.makeText(MainActivity.this, "Your Answer is Wrong ", Toast.LENGTH_LONG).show();}
                index++;
                updateFragment(qbank.questionslist.get(index).getQuestion(),qbank.getColorlist().get(index));
                progress = progress+10;
                progressBar.setProgress(progress);
                progressBar.setMax(60);
                            }
        });
   }
    private void updateFragment(int Questid,int colorid){
        System.out.println("Index in update is : "+index);
        FragmentManager manager = getSupportFragmentManager();
        manager.findFragmentById(R.id.mainframelayout);//Connected
        myFragment myfragobj = myFragment.newInstance(Questid,colorid);
        //layout is connecting with the fragmentclass(name of the frameLayout name) using the fragment object
        manager.beginTransaction().add(R.id.mainframelayout,myfragobj).commit();
                  }
}