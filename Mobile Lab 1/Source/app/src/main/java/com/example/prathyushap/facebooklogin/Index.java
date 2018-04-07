package com.example.prathyushap.facebooklogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Index extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
    }
    public void SportsNews(View v)
    {
        Intent intent = new Intent(Index.this,SportNews.class);
        startActivity(intent);
    }
    public void ContactUs(View v)
    {
        Intent intent = new Intent(Index.this,ContactUs.class);
        startActivity(intent);
    }
}
