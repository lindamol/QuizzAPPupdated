package com.example.quizapp_android;

import java.util.ArrayList;

public class QuestionBank {
    ArrayList<Question> questionslist = new ArrayList<Question>(0);
    ArrayList<Integer>  colorlist = new ArrayList<>();

    public QuestionBank(){
        colorlist.add(R.color.Aquamarine);
        colorlist.add(R.color.Lime);
        colorlist.add(R.color.Olive);
        colorlist.add(R.color.LightSkyBlue);
        colorlist.add(R.color.Pink);
        Question q1 = new Question(R.string.Queestion1,false,colorlist.get(0));
        Question q2 = new Question(R.string.Queestion2,true,colorlist.get(1));
        Question q3 = new Question(R.string.Queestion3,false,colorlist.get(2));
        Question q4 = new Question(R.string.Queestion4, true,colorlist.get(3));
        questionslist.add(q1);
        questionslist.add(q2);
        questionslist.add(q3);
        questionslist.add(q4);

    }

}
