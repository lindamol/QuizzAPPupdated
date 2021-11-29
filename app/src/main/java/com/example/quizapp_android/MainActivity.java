package com.example.quizapp_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLOutput;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    Button trueButton, falseButton;
    ProgressBar progressBar;
    int index = 0;
    double average = 0;
    int attemptcount = 0;
    int correctAns = 0;
    QuestionBank qbank = new QuestionBank();
    Storage storage;
    int Qntbanklength = 0;
    int listsize = qbank.questionslist.size();
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        trueButton = findViewById(R.id.button);
        falseButton = findViewById(R.id.button2);
        progressBar = findViewById(R.id.progressBar);
        Qntbanklength = listsize;
                storage = ((myApp)getApplication()).getStorage();
          if (savedInstanceState != null) {
            // Restore value of members from saved state
            index = savedInstanceState.getInt("currentIndex");
            correctAns = savedInstanceState.getInt("correctanswers");
            Qntbanklength = savedInstanceState.getInt("qbnklength");
              updateProgressBar();
            updateFragment(qbank.questionslist.get(index).getQuestion(),qbank.getColorlist().get(index));
                   } else {
           // Probably initialize members with default values for a new instance
            index = 0;
            updateFragment(qbank.questionslist.get(index).getQuestion(),qbank.getColorlist().get(index));
                             }
        //True Button
           trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index == listsize-1)
                { checkTrueorFalse();
                alertDialog();
                updateProgressBar();
                }
                else { checkTrue();}
                index++;
                updateFragment(qbank.questionslist.get(index).getQuestion(),qbank.getColorlist().get(index));
                updateProgressBar();
                               }
           });
        //False Button
           falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //4
                if(index == listsize-1)
                {  checkTrueorFalse();
                    updateProgressBar();
                alertDialog();
                             }
                else { checkFalse(); }
                index++;
                updateProgressBar();
                updateFragment(qbank.questionslist.get(index).getQuestion(),qbank.getColorlist().get(index));
                                    }
               });
   }
      //MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        //return super.onCreateOptionsMenu(menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.average: {
                                 storage.getResult(MainActivity.this);
                                 showReport();
                                 break; }
            case R.id.numofquest:{   FragmentManager fragmanager = getSupportFragmentManager();
                fragmanager.findFragmentById(R.id.mainframelayout);//Connected
                AddnumberQuestion fragment1 = new AddnumberQuestion();
                //layout is connecting with the fragmentclass(name of the frameLayout name) using the fragment object
                fragmanager.beginTransaction().replace(R.id.mainframelayout,fragment1).commit();
                falseButton.setVisibility(View.INVISIBLE);
                trueButton.setVisibility(View.INVISIBLE);
                try {
                    Intent intent = getIntent();
                    int newQuestioncount = intent.getIntExtra("newnumber",0);
                    System.out.println("This is my question count:"+newQuestioncount);
                    listsize = newQuestioncount;
                } catch(Exception e) {
                    e.printStackTrace();
                }

                               break; }
            case R.id.newQuestion:{
                FragmentManager fragmanager = getSupportFragmentManager();
                fragmanager.findFragmentById(R.id.mainframelayout);//Connected
                AddQuestionFragment fragment = new AddQuestionFragment();
                //layout is connecting with the fragmentclass(name of the frameLayout name) using the fragment object
                fragmanager.beginTransaction().replace(R.id.mainframelayout,fragment).commit();
                falseButton.setVisibility(View.INVISIBLE);
                trueButton.setVisibility(View.INVISIBLE);
                try {
                    Intent intent = getIntent();
                    String newQuestion = intent.getStringExtra("name");
                    Boolean newanswer = intent.getBooleanExtra("answer",false);
                    System.out.println("My new Question is"+newQuestion);
                    System.out.println("My new Answer is"+newanswer);

                } catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case R.id.reset:{
                storage.resetResult(MainActivity.this);
                index=0;
                attemptcount=0;
                storage.average =0;
                updateProgressBar();
                break; }
        }
        return  true ;
    }

    private void updateFragment(int Questid, int colorid){
        //System.out.println("Index in update is : "+index);
        //System.out.println("Index in list is : "+listsize+"..."+qbank.questionslist.size());
        FragmentManager manager = getSupportFragmentManager();
        manager.findFragmentById(R.id.mainframelayout);//Connected
        myFragment myfragobj = myFragment.newInstance(Questid,colorid);
        //layout is connecting with the fragmentclass(name of the frameLayout name) using the fragment object
        manager.beginTransaction().add(R.id.mainframelayout,myfragobj).commit();
           }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("currentIndex",index);
        savedInstanceState.putInt("qbnklength",Qntbanklength);
        savedInstanceState.putInt("correctanswers",correctAns);
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }
    private void alertDialog() {//First Alert
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        //dialog.setTitle("CONGRATULATIONS!!!!!");
        dialog.setMessage(getResources().getString(R.string.correctanswer)+""+correctAns+ getResources().getString(R.string.outof) +listsize);
        dialog.setPositiveButton(getResources().getString(R.string.savebutton),new DialogInterface.OnClickListener() //for Save Button positive
        {
            public void onClick(DialogInterface dialog,
                                int which) {
                attemptcount++;
                storage.saveResult(MainActivity.this,listsize,correctAns);
                //attemptcount=0;
               correctAns = 0;
                index=0;
                Toast.makeText(getApplicationContext(),"Save is clicked",Toast.LENGTH_SHORT).show();
                Collections.shuffle(qbank.colorlist);
                Collections.shuffle(qbank.questionslist);
                progressBar.setProgress(index);
               //updateProgressBar();// 6
            }
        });
        dialog.setNegativeButton(getResources().getString(R.string.ignorebutton),new DialogInterface.OnClickListener() { // for IGNORE button Negative
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"Ignore is clicked",Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
        Collections.shuffle(qbank.colorlist);
        Collections.shuffle(qbank.questionslist);
        index = 0;
        //correctAns=0;
        progressBar.setProgress(index);
        updateFragment(qbank.questionslist.get(index).getQuestion(),qbank.getColorlist().get(index));
          }
    private void checkTrueorFalse() {
        if (qbank.questionslist.get(index).getAnswer() == true) {
            checkTrue();
        }
        else {checkFalse();}
    }
    private void checkTrue(){
        if(qbank.questionslist.get(index).getAnswer()== true) {
            correctAns++;
            Toast.makeText(MainActivity.this, getResources().getString(R.string.correcttoast), Toast.LENGTH_SHORT).show();
        }
        else { Toast.makeText(MainActivity.this, getResources().getString(R.string.wrongtoast), Toast.LENGTH_SHORT).show();}
    }
    private void checkFalse()
    {
        if(qbank.questionslist.get(index).getAnswer()== false)
        {correctAns++;
            Toast.makeText(MainActivity.this, getResources().getString(R.string.correcttoast), Toast.LENGTH_SHORT).show();
        }else{Toast.makeText(MainActivity.this, getResources().getString(R.string.wrongtoast), Toast.LENGTH_SHORT).show();}
    }
    private void updateProgressBar()
    { progressBar.setProgress(index);//0,1,2,,3,4
      progressBar.setMax(listsize-1);//9
    }
    private void showReport() {
            double average = storage.findAverage();
            AlertDialog.Builder dialog=new AlertDialog.Builder(this);//storage.scoreslist.size()
        dialog.setMessage(getResources().getString(R.string.average)+storage.findAverage()+ " "+getResources().getString(R.string.nxtavfor)+attemptcount+" "+getResources().getString(R.string.attempts));
       // dialog.setTitle("Your total correct answers in "+storage.scoreslist.size()+" attempts is "+storage.sum);
                  dialog.setPositiveButton("OK",new DialogInterface.OnClickListener() //for Save Button positive
        { public void onClick(DialogInterface dialog,
                                int which) {
                           }
        });
        dialog.setNegativeButton(getResources().getString(R.string.savebutton),new DialogInterface.OnClickListener() { // for save button Negative
            @Override
            public void onClick(DialogInterface dialog, int which) {
                           }
        });
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
            }

   }