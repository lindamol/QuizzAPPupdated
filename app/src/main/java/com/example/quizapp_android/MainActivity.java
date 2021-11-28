package com.example.quizapp_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.sql.SQLOutput;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    Button trueButton, falseButton;
    ProgressBar progressBar;
    int index = 0;
    int attemptcount = 0;
    int correctAns = 0;
    QuestionBank qbank = new QuestionBank();
    Storage storage;
    int Qntbanklength = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        trueButton = findViewById(R.id.button);
        falseButton = findViewById(R.id.button2);
        progressBar = findViewById(R.id.progressBar);
        Qntbanklength = qbank.questionslist.size();
        storage = ((myApp)getApplication()).getStorage();
          if (savedInstanceState != null) {
            // Restore value of members from saved state
            index = savedInstanceState.getInt("currentIndex");
            Qntbanklength = index;
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
                if(index == qbank.questionslist.size()-1)
                { alertDialog();}
                else
                {
                   if(qbank.questionslist.get(index).getAnswer()== true) {
                    correctAns++;
                    Toast.makeText(MainActivity.this, "Your Answer is Correct ", Toast.LENGTH_SHORT).show();
                    }
                    else { Toast.makeText(MainActivity.this, "Your Answer is Wrong ", Toast.LENGTH_SHORT).show();}
                    index++;
                    updateFragment(qbank.questionslist.get(index).getQuestion(),qbank.getColorlist().get(index));
                    updateProgressBar();}
                 }
           });
        //False Button
           falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index == qbank.questionslist.size()-1)
                {alertDialog();}
                else{
                if(qbank.questionslist.get(index).getAnswer()== false)
                  {correctAns++;
                    Toast.makeText(MainActivity.this, "Your Answer is Correct ", Toast.LENGTH_SHORT).show();
                  }else{Toast.makeText(MainActivity.this, "Your Answer is Wrong ", Toast.LENGTH_SHORT).show();}
                index++;
                updateFragment(qbank.questionslist.get(index).getQuestion(),qbank.getColorlist().get(index));
                updateProgressBar();}
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
            case R.id.average: { storage.getResult(MainActivity.this);
                                 showReport();
                               break; }
            case R.id.numofquest:{

//                System.out.println("Number of questions in each Attempt"+storage.totalqnslist);
//                System.out.println("Number of questions in each Attempt"+storage.scoreslist);
                break; }
            case R.id.reset:{
                storage.resetResult(MainActivity.this);
                break; }
        }
        return  true ;
    }

    private void updateFragment(int Questid, int colorid){
        System.out.println("Index in update is : "+index);
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
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }
    private void alertDialog() {//First Alert
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setTitle("CONGRATULATIONS!!!!!");
        dialog.setMessage("YOUR TOTAL SCORE IS  "+correctAns+ " out of " +qbank.questionslist.size());
        dialog.setPositiveButton("SAVE",new DialogInterface.OnClickListener() //for Save Button positive
        {
            public void onClick(DialogInterface dialog,
                                int which) {
                attemptcount++;
                storage.saveResult(MainActivity.this,qbank.questionslist.size(),correctAns);
                correctAns = 0;
                Toast.makeText(getApplicationContext(),"Save is clicked",Toast.LENGTH_LONG).show();
               Collections.shuffle(qbank.colorlist);
                Collections.shuffle(qbank.questionslist);
            }
        });
        dialog.setNegativeButton("IGNORE",new DialogInterface.OnClickListener() { // for IGNORE button Negative
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"Ignore is clicked",Toast.LENGTH_LONG).show();

            }
        });
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
        Collections.shuffle(qbank.colorlist);
        Collections.shuffle(qbank.questionslist);
        index = 0;
        updateFragment(qbank.questionslist.get(index).getQuestion(),qbank.getColorlist().get(index));
        updateProgressBar();
    }
    private void updateProgressBar()
    { progressBar.setProgress(index);
      progressBar.setMax(qbank.questionslist.size()-1);
    }
    private void showReport() {
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setMessage("Your Average is "+storage.findAverage()+ " for "+storage.scoreslist.size() +" attempts");
        dialog.setTitle("Your total correct answers in "+storage.scoreslist.size()+" attempts is "+storage.sum);
        dialog.setPositiveButton("OK",new DialogInterface.OnClickListener() //for Save Button positive
        { public void onClick(DialogInterface dialog,
                                int which) {
                           }
        });
        dialog.setNegativeButton("SAVE",new DialogInterface.OnClickListener() { // for save button Negative
            @Override
            public void onClick(DialogInterface dialog, int which) {
                           }
        });
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
            }
}