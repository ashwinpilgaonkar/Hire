package com.omnify.hire;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    static int randomNum[] = new int[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Load both fragments into top and bottom pane of the screen
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_unsorted, new UnsortedFragment())
                .replace(R.id.fragment_sorted, new SortedFragment())
                .commit();
    }

    @OnClick(R.id.regen_btn)
    void RegenButton(View view) {
        if (view.getId() == R.id.regen_btn) {

            //Refresh top fragment
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_unsorted, new UnsortedFragment())
                    .commit();
        }
    }

    @OnClick(R.id.sort_btn)
    void SortButton(View view) {
        if (view.getId() == R.id.sort_btn) {

            //Start sorting service
            Intent i= new Intent(getApplicationContext(), SortingService.class);
            startService(i);
        }
    }
}
