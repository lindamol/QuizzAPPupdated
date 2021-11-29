package com.example.quizapp_android;

import android.app.Activity;
import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    double sum = 0;
    double average = 0;
    int myattempt = 0;
    ArrayList<Integer> scoreslist = new ArrayList<>(0);
    ArrayList<Integer> totalqnslist = new ArrayList<>(0);
    String filename = "result1.txt";
    public void saveResult(Activity activity, int qtotal, int correctanswer){
        FileOutputStream fileOutputStream = null;
        String input = qtotal +"*"+correctanswer+"&";
        try {
            fileOutputStream = activity.openFileOutput(filename, Context.MODE_PRIVATE);
            fileOutputStream.write(input.getBytes());
           // fileOutputStream.write(String.valueOf(correctanswer).getBytes());
        } catch (IOException e) {
            e.printStackTrace();//print all previous errors
        }
        finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public  void getResult(Activity activity){
        FileInputStream fileInputStream = null;
        StringBuffer stringBuffer = new StringBuffer();
        int read = 0; //   20/34&13 9&
        try {
            fileInputStream = activity.openFileInput(filename);
            while((read= fileInputStream.read()) != -1){//read char by char
               stringBuffer.append((char)read);
                System.out.println("Reading File 44");
                                      } // need a function to convert stringbuffer to the format which i want,here int
            stringtoattempts(stringBuffer.toString());
            System.out.println("Reading File 47");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //Erase
    public  void resetResult(Activity activity){
        scoreslist.clear();
        totalqnslist.clear();
        FileOutputStream fileOutputStream = null;
                try { //open and write from start, that is it erase by adding empty String
            fileOutputStream = activity.openFileOutput(filename, Context.MODE_PRIVATE);
            fileOutputStream.write("".getBytes());
                  } catch (IOException e) {
            e.printStackTrace();//print all previous errors
        }
        finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
    private void stringtoattempts(String filestring){
        int index = 0;
        System.out.println("Reading File 75");
        for(int i = 0;i<filestring.toCharArray().length;i++)
        { if(filestring.toCharArray()[i]=='&') {
            String attempt1 = filestring.substring(index, i);
            System.out.println("print attempt1"+attempt1);
            attemptstonumber(attempt1);
            index = i+1;
        }
        }
    }
    private  void attemptstonumber(String attempt){
        System.out.println("Attempt in 86"+attempt);
        for(int i = 0; i< attempt.toCharArray().length;i++)
            if(attempt.toCharArray()[i] == '*')
            {   String totalques = attempt.substring(0,i);
                String totalans = attempt.substring(i+1,attempt.toCharArray().length);
                scoreslist.add(Integer.parseInt(totalans));
                totalqnslist.add(Integer.parseInt(totalques));
            }
    }
    public double findAverage(){
        average = 0;
        myattempt++;
        sum=0;
        for (int i : scoreslist) {
            sum += i;
        }
        return average = sum /scoreslist.size();
            }
}
