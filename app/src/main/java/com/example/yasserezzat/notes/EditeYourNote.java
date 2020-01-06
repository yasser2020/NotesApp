package com.example.yasserezzat.notes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.HashSet;

public class EditeYourNote extends AppCompatActivity implements TextWatcher {
    int noteId;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edite_your_notw);
         editText=(EditText)findViewById(R.id.editText);
        Intent i =getIntent();
       noteId= i.getIntExtra("noteId",-1);
        if(noteId != -1) {
            editText.setText(MainActivity.notes.get(noteId));
        }
        editText.addTextChangedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edite_your_notw, menu);
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

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        MainActivity.notes.set(noteId, String.valueOf(s));
        MainActivity.arrayAdapter.notifyDataSetChanged();
        SharedPreferences sharedPreferences=this.getSharedPreferences("com.example.yasserezzat.notes", Context.MODE_PRIVATE);
        if(MainActivity.set == null)
        {
            MainActivity.set=new HashSet<String>();
        }
        else {
            MainActivity.set.clear();
        }
        MainActivity.set.addAll(MainActivity.notes);
        sharedPreferences.edit().remove("notes").apply();
        sharedPreferences.edit().putStringSet("notes",MainActivity.set).apply();


    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
