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

import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    Button trueButton, falseButton;
    ProgressBar progressBar;
    int index = 0;
    int falseclicks =0;
    int trueclicks = 0;
    int attemptcount = 0;
    int correctAns = 0;
    QuestionBank qbank = new QuestionBank();

    int Qntbanklength = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        trueButton = findViewById(R.id.button);
        falseButton = findViewById(R.id.button2);
        progressBar = findViewById(R.id.progressBar);
        Qntbanklength = qbank.questionslist.size();
       // Collections.shuffle(qbank.colorlist); //Shuffle Color
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
                { alertDialog();
                    index = 0;
                    updateFragment(qbank.questionslist.get(index).getQuestion(),qbank.getColorlist().get(index));
                    updateProgressBar();
                }
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
                {
                 alertDialog();
                    index = 0;
                    updateFragment(qbank.questionslist.get(index).getQuestion(),qbank.getColorlist().get(index));
                    updateProgressBar();
                }
                else{
                if(qbank.questionslist.get(index).getAnswer()== false)
              {correctAns++;
              Toast.makeText(MainActivity.this, "Your Answer is Correct ", Toast.LENGTH_SHORT).show();
             //System.out.println("(InsideFalse)no of correct : "+correctAns);
              }else{Toast.makeText(MainActivity.this, "Your Answer is Wrong ", Toast.LENGTH_SHORT).show();}
                index++;
                updateFragment(qbank.questionslist.get(index).getQuestion(),qbank.getColorlist().get(index));
                //progress = progress+10;
                updateProgressBar();}
                            }
               });
           //Menu
    }

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
                break;
            }
            case R.id.numofquest:{
                break;
            }
            case R.id.reset:{
                break;
            }
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
    private void alertDialog() {
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
       // String prodname = ((myAPP)getApplication()).getManager().productArray.get(selectedPosition).getProductname();
        dialog.setTitle("CONGRATULATIONS!!!!!");
        dialog.setMessage("YOUR TOTAL SCORE IS  "+correctAns+ " out of " +qbank.questionslist.size());
        dialog.setPositiveButton("SAVE",new DialogInterface.OnClickListener() //for Save Button positive
        {
            public void onClick(DialogInterface dialog,
                                int which) {
                attemptcount++;
                Toast.makeText(getApplicationContext(),"Yes is clicked",Toast.LENGTH_LONG).show();
                Collections.shuffle(qbank.colorlist);
            }
        });
        dialog.setNegativeButton("IGNORE",new DialogInterface.OnClickListener() { // for cancel button Negative
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"Ignore is clicked",Toast.LENGTH_LONG).show();

            }
        });
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
        Collections.shuffle(qbank.colorlist);
    }
    private void updateProgressBar()
    {
        progressBar.setProgress(index);
        progressBar.setMax(qbank.questionslist.size());
    }
}