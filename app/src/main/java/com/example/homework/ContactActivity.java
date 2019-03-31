package com.example.homework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ContactActivity extends AppCompatActivity {

    private int pst = 0;
    private int c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Spinner contactSpinner = (Spinner)findViewById(R.id.spinnerC);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.contactsArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        contactSpinner.setAdapter(adapter);

        Intent cancelIntent = getIntent();
        c = cancelIntent.getIntExtra(MainActivity.CONTACT_ID,0);

        contactSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pst = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void setContact(View v){
        Intent end = new Intent();
        end.putExtra(MainActivity.CONTACT_ID, pst);
        setResult(RESULT_OK,end);
        finish();
    }

    public void cancelContact(View v){
        Intent end = new Intent();
        end.putExtra(MainActivity.CONTACT_ID, c);
        setResult(RESULT_OK,end);
        finish();
    }


}
