package com.example.sampath.mobilelab3;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Locale;

public class Antonym extends AppCompatActivity {

    SQLLiteDB sqlLiteDB;
    TextToSpeech toSpeech;
    SQLiteDatabase myDatabase;
    int result;
    private static final int REQ_CODE = 100;
    int word = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antonym);
        sqlLiteDB = new SQLLiteDB(this);

        toSpeech = new TextToSpeech(Antonym.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i==TextToSpeech.SUCCESS)
                {
                    result=toSpeech.setLanguage(Locale.UK);
                }

            }
        });
    }

    public void recordPython(View view)
    {
        word = 1;
        startVoiceInput();
    }

    public void recordWeb(View view)
    {
        word = 2;
        startVoiceInput();
    }

    public void recordAP(View view)
    {
        word = 3;
        startVoiceInput();
    }

    public void recordDL(View view)
    {
        word = 4;
        startVoiceInput();
    }

    public void defPython(View view)
    {

        searchDatabase(1);
    }

    public void defWeb(View view)
    {

        searchDatabase(2);
    }

    public void defAP(View view)
    {

        searchDatabase(3);
    }

    public void defDL(View view)
    {

        searchDatabase(4);
    }

    public void startVoiceInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hi");
        try {
            startActivityForResult(intent, REQ_CODE);
        } catch (ActivityNotFoundException a) {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    int totalNum = result.size() - 1;
                    if(word==1)
                    {
                        //sqlLiteDB.create(result.get(totalNum),"Python");
                        sqlLiteDB.update(1,result.get(totalNum),"Right is Left");
                    }
                    else if(word==2)
                    {
                        //sqlLiteDB.create(result.get(totalNum),"Web");
                        sqlLiteDB.update(2,result.get(totalNum),"Good is Bad");
                    }
                    else if(word==3)
                    {
                        //sqlLiteDB.create(result.get(totalNum),"AP");
                        sqlLiteDB.update(3,result.get(totalNum),"Light is Dark");
                    }
                    else if(word==4)
                    {
                        //sqlLiteDB.create(result.get(totalNum),"DL");
                        sqlLiteDB.update(4,result.get(totalNum),"Tall is Short");
                    }
                    word = 0;
                    displayDatabase();
                }
                break;
            }

        }
    }

    public void displayDatabase()
    {
        Cursor cursor = sqlLiteDB.retrieve();


        if (cursor.moveToFirst()) {
            do {



                Log.i("MainString",cursor.getInt(0) + ", " + cursor.getString(1) + ", " + cursor.getString(2));

            }while (cursor.moveToNext());
        }
    }

    public void searchDatabase(int number)
    {
        Cursor cursor = sqlLiteDB.retrieve();


        if (cursor.moveToFirst()) {
            do {
                if(cursor.getInt(0)==number)
                {
                    toSpeech.speak(cursor.getString(1),TextToSpeech.QUEUE_FLUSH,null);
                }


                Log.i("MainString",cursor.getInt(0) + ", " + cursor.getString(1) + ", " + cursor.getString(2));

            }while (cursor.moveToNext());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(toSpeech != null)
        {
            toSpeech.stop();
            toSpeech.shutdown();
        }
    }
}
