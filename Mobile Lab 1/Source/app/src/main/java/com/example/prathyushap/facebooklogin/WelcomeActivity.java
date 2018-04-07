package com.example.prathyushap.facebooklogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }
    public void SignIn(View v)
    {
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);
    }
    public void SignUp(View v)
    {
        Intent intent = new Intent(WelcomeActivity.this, Registration.class);
        startActivity(intent);
    }
}
