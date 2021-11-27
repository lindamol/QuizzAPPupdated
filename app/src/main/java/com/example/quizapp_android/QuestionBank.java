package com.example.quizapp_android;

import java.util.ArrayList;
import java.util.Collections;

public class QuestionBank {
    ArrayList<Question> questionslist = new ArrayList<Question>(0);
    ArrayList<Integer>  colorlist = new ArrayList<>();

    public ArrayList<Integer> getColorlist() {
        return colorlist;
    }

    public void setColorlist(ArrayList<Integer> colorlist) {
        this.colorlist = colorlist;
    }

    public QuestionBank(){
        colorlist.add(R.color.Aquamarine);
        colorlist.add(R.color.Lime);
        colorlist.add(R.color.Olive);
        colorlist.add(R.color.LightSkyBlue);
        colorlist.add(R.color.Pink);
        colorlist.add(R.color.Salmon);
        colorlist.add(R.color.purple_200);
        Question q1 = new Question(R.string.Question1,false,colorlist.get(0));
        Question q2 = new Question(R.string.Question2,true,colorlist.get(1));
        Question q3 = new Question(R.string.Question3,false,colorlist.get(2));
        Question q4 = new Question(R.string.Question4, true,colorlist.get(3));
        Question q5 = new Question(R.string.Question5,true,colorlist.get(4));
        Question q6 = new Question(R.string.Question6,true,colorlist.get(5));
        questionslist.add(q1);
        questionslist.add(q2);
        questionslist.add(q3);
        questionslist.add(q4);
        questionslist.add(q5);
        questionslist.add(q6);

    }
//     public int calculateMarks(int falseclicks,int trueclicks){
//        int marks = trueclicks;
//        return marks;
//
//     }

}
