package com.example.happyvoice;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView grabar;
    Button idi;
    int idioma;
    private static final int RECOGNIZE_SPEECH_ACTIVITY = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grabar = findViewById(R.id.txtGrabarVoz);
        idi = findViewById(R.id.b_idioma);
        idioma=1;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RECOGNIZE_SPEECH_ACTIVITY:
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> speech = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String strSpeech2Text = speech.get(0);
                    switch (idioma){
                        case 1:
                            grabar.setText("Voz reconocida:\n"+strSpeech2Text);
                            break;
                        case 2:
                            grabar.setText("Recognized voice:\n"+strSpeech2Text);
                            break;
                        case 3:
                            grabar.setText("Veu reconeguda:\n"+strSpeech2Text);
                            break;


                    }
                }
                break;
            default:
                break;
        }
    }
    public void onClickImgBtnHablar(View v) {
        Intent intentActionRecognizeSpeech = new Intent(
                RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        switch (idioma){
            case 1:
                intentActionRecognizeSpeech.putExtra(
                        RecognizerIntent.EXTRA_LANGUAGE, "es-ES");
                break;
            case 2:
                intentActionRecognizeSpeech.putExtra(
                        RecognizerIntent.EXTRA_LANGUAGE, "en-US");
                break;
            case 3:
                intentActionRecognizeSpeech.putExtra(
                        RecognizerIntent.EXTRA_LANGUAGE, "ca-ES");
                break;

        }

        try {
            startActivityForResult(intentActionRecognizeSpeech,
                    RECOGNIZE_SPEECH_ACTIVITY);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    "Tú dispositivo no soporta el reconocimiento por voz",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void idioma(View view) {
        idioma++;
        if(idioma==4){idioma=1;}
        switch (idioma){
            case 1:
                idi.setText("ESP");
                break;
            case 2:
                idi.setText("ENG USA");
                break;
            case 3:
                idi.setText("CAT");
                break;
        }
    }
}
