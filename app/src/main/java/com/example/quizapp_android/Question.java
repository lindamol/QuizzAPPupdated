package com.example.quizapp_android;

public class Question {
        int Question;
        Boolean Answer;
        int Color;

    public Question(int question, boolean answer, int color) {
        this.Question = question;
        this.Answer = answer;
        this.Color = color;
    }

    public int getQuestion() {
        return Question;
    }

    public boolean getAnswer() {
        return Answer;
    }

    public int getColor() {
        return Color;
    }

    public void setQuestion(int question) {
        Question = question;
    }

    public void setAnswer(boolean answer) {
        Answer = answer;
    }

    public void setColor(int color) {
        Color = color;
    }
}
