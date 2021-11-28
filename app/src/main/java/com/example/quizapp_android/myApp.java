package com.example.quizapp_android;

import android.app.Application;

public class myApp extends Application {
    public Storage storage = new Storage();
    public QuestionBank qbank = new QuestionBank();

    public QuestionBank getQbank() {
        return qbank;
    }

    public Storage getStorage() {
        return storage;
    }
}
