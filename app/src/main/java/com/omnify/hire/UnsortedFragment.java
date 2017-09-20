package com.omnify.hire;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UnsortedFragment extends Fragment {

    @BindView(R.id.randomno_array) TextView RandomNoText;

    public UnsortedFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_unsorted, container, false);
        ButterKnife.bind(this, v);

        RandomNoText.setText(generateRandomNumString());

        return v;
    }

    private String generateRandomNumString(){
        String randomNoStr="";
        Random rand = new Random();
        //For generating two digit random numbers
        int minimum = 10;
        int maximum = 99;

        //create a string of 20 random numbers
        for(int i=0; i<20; i++) {
            MainActivity.randomNum[i] = minimum + rand.nextInt((maximum - minimum) + 1);
            randomNoStr = randomNoStr + String.valueOf(MainActivity.randomNum[i]) + " ";
        }

        return randomNoStr;
    }
}
