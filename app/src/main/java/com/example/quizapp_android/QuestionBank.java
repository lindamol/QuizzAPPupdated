package com.example.quizapp_android;

import java.util.ArrayList;
import java.util.Collections;

public class QuestionBank {
    ArrayList<Question> questionslist = new ArrayList<Question>(0);
    ArrayList<Integer>  colorlist = new ArrayList<>(0);

    public ArrayList<Integer> getColorlist() {
        return colorlist;
    }

    public void setColorlist(ArrayList<Integer> colorlist) {
        this.colorlist = colorlist;
    }

    public QuestionBank(){
        colorlist.add(R.color.CadetBlue);
        colorlist.add(R.color.Brown);
        colorlist.add(R.color.Olive);
        colorlist.add(R.color.Indigo);
        colorlist.add(R.color.Tomato);
        colorlist.add(R.color.Teal);
        colorlist.add(R.color.BlueViolet);
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
       // Collections.shuffle(colorlist);


    }

}
