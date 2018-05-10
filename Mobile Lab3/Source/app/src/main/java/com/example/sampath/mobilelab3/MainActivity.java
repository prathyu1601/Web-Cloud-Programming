package com.example.sampath.mobilelab3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void synonym(View V)
    {
        Intent synonym = new Intent(MainActivity.this,Synonym.class);
        startActivity(synonym);
    }

    public void antonym(View V)
    {
        Intent antonym = new Intent(MainActivity.this,Antonym.class);
        startActivity(antonym);
    }
}
