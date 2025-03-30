package com.ab.newsapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.widget.Toolbar; // Correct import

import com.iammert.library.readablebottombar.ReadableBottomBar;

public class MainActivity extends AppCompatActivity {

    ReadableBottomBar readableBottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Correct Toolbar Usage
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false); // Hides default title


        readableBottomBar = findViewById(R.id.readableBottomBar3);

        // Default Fragment Load
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contant, new HomeFragment());
        fragmentTransaction.commit();

        readableBottomBar.setOnItemSelectListener(i -> {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            if (i == 0) {
                transaction.replace(R.id.contant, new HomeFragment());
            } else if (i == 1) {
                transaction.replace(R.id.contant, new ScienceFragment());
            } else if (i == 2) {
                transaction.replace(R.id.contant, new SportsFragment());
            } else if (i == 3) {
                transaction.replace(R.id.contant, new HealthFragment());
            } else if (i == 4) {
                transaction.replace(R.id.contant, new EnterFragment());
            }

            transaction.commit();
        });
    }
}
