package com.example.quizapp_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class AddQuestionFragment extends DialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddQuestionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     //* @param param1 Parameter 1.
     //* @param param2 Parameter 2.
     * @return A new instance of fragment AddQuestionFragment.
     */
    // TODO: Rename and change types and number of parameters
//    public static AddQuestionFragment newInstance(String param1, String param2) {
//        AddQuestionFragment fragment = new AddQuestionFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       View v = inflater.inflate(R.layout.fragment_add_question, container, false);
        EditText editQuestion = v.findViewById(R.id.addquestion_text);
        EditText editAnswer = v.findViewById(R.id.answer_text);
        Button saveButton = v.findViewById(R.id.save_id);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Save clicked");
                if (!editQuestion.getText().toString().isEmpty()){
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    String newQuestion = editQuestion.getText().toString();
                   Boolean newanswer = Boolean.parseBoolean(String.valueOf(editAnswer.getText()));
                   intent.putExtra("name", newQuestion);
                    System.out.println("My question in fragment is "+newQuestion);
                    intent.putExtra("name",newQuestion);
                   intent.putExtra("answer",newanswer);
                    startActivity(intent);
                         }
            }
        });
       return  v;
    }
}