package com.example.quizapp_android;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link myFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class myFragment extends Fragment {


    // TODO: Rename and change types and number of parameters
    public static myFragment newInstance(int Qid, int colorid) {
        myFragment fragment = new myFragment();
        Bundle args = new Bundle();
        args.putInt("Questid", Qid);
        args.putInt("Colorid", colorid);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        TextView Qtext = (TextView)view.findViewById(R.id.Qtext);
        Qtext.setText(getArguments().getInt("Questid"));
        Qtext.setBackgroundResource(getArguments().getInt("Colorid"));
        return view;
    }
}