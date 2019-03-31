package com.example.homework;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String CONTACT_ID = "contact id";
    public static final int CONTACT_REQUEST = 1;
    private int curr_contact = 0;

    public static final String SOUND_ID = "sound id";
    public static final int SOUND_REQUEST = 2;
    private int curr_sound = 0;

    private MediaPlayer buttonPlayer;
    static public Uri[] sounds;

    String names [] = {"Michał Parchaś", "Paweł Jumper", "Pat"};

    boolean test = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Button contactButton = (Button)findViewById(R.id.contact);
        final Button soundButton = (Button)findViewById(R.id.sound);

        sounds = new Uri[4];
        sounds[0] = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.ring01);
        sounds[1] = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.ring02);
        sounds[2] = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.ring03);
        sounds[3] = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.ring04);

        buttonPlayer = new MediaPlayer();
        buttonPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);


        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent setContact = new Intent(getApplicationContext(),ContactActivity.class);
                setContact.putExtra(CONTACT_ID, curr_contact);
                startActivityForResult(setContact, CONTACT_REQUEST);
            }
        });

        soundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent setSound = new Intent(getApplicationContext(),SoundActivity.class);
                setSound.putExtra(SOUND_ID, curr_contact);
                startActivityForResult(setSound, SOUND_REQUEST);
            }
        });

        final FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!test){
                    setButtonPlayer();
                    buttonPlayer.start();
                    fab.setImageResource(android.R.drawable.ic_media_pause);
                    test = true;
                }
                else {
                    buttonPlayer.stop();
                    fab.setImageResource(android.R.drawable.ic_media_play);
                    test = false;
                }
            }
        });
        setContact();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            if (requestCode == CONTACT_REQUEST) {
                curr_contact = data.getIntExtra(CONTACT_ID, 0);
                setContact();
            }
            if (requestCode == SOUND_REQUEST) {
                curr_sound = data.getIntExtra(SOUND_ID, 0);
            }
        }
    }

    private void setButtonPlayer(){
        switch (curr_sound){
            case 0:
                buttonPlayer = MediaPlayer.create(this, R.raw.ring01);
                break;
            case 1:
                buttonPlayer = MediaPlayer.create(this, R.raw.ring02);
                break;
            case 2:
                buttonPlayer = MediaPlayer.create(this, R.raw.ring03);
                break;
            case 3:
                buttonPlayer = MediaPlayer.create(this, R.raw.ring04);
                break;
        }
    }

    private void setContact(){
        TextView name = findViewById(R.id.contact_name);
        name.setText(names[curr_contact]);
        ImageView img = findViewById(R.id.cont_img);
        switch (curr_contact){
            case 0:
                img.setImageResource(R.drawable.img1);
                break;
            case 1:
                img.setImageResource(R.drawable.img2);
                break;
            case 2:
                img.setImageResource(R.drawable.img3);
                break;
        }
    }
}
