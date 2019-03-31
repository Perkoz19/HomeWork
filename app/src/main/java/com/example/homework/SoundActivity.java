package com.example.homework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SoundActivity extends AppCompatActivity {
    private int selected_sound = 0;
    private int c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);
        Intent received_intent = getIntent();
        c = received_intent.getIntExtra(MainActivity.SOUND_ID, 0);

        final Button cancelButton = (Button)findViewById(R.id.cancelS);
        final Button okButton = (Button)findViewById(R.id.okS);
    }
    public void onRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();
        if (checked){
            //RadioGroup group = (RadioGroup) findViewById(R.id.radioGroup);
            switch (view.getId()){
                case R.id.sound1: selected_sound = 0; break;
                case R.id.sound2: selected_sound = 1; break;
                case R.id.sound3: selected_sound = 2; break;
                case R.id.sound4: selected_sound = 3; break;
            }
        }
    }

    public void setSound(View v){
        Intent end = new Intent();
        end.putExtra(MainActivity.SOUND_ID, selected_sound);
        setResult(RESULT_OK,end);
        finish();
    }

    public void cancelSound(View v){
        Intent end = new Intent();
        end.putExtra(MainActivity.SOUND_ID, c);
        setResult(RESULT_OK,end);
        finish();
    }
}
