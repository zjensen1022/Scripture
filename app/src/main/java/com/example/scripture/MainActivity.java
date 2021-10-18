package com.example.scripture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    public static final String SCRIPTURE = "com.example.scripture.SCRIPTURE";
    public static final String BLANK_DEFAULT = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void display(View view) {

        Intent intent = new Intent(this, ViewScripture.class);

        EditText bookEditText = findViewById(R.id.bookEditText);
        EditText chapterEditText = findViewById(R.id.chapterEditText);
        EditText verseEditText = findViewById(R.id.verseEditText);

        String book = bookEditText.getText().toString().trim();
        String chapter = chapterEditText.getText().toString().trim();
        String verse = verseEditText.getText().toString().trim();

        String scripture = String.format("%s %s:%s", book, chapter, verse);

        Log.d(TAG, "Creating intent with " + scripture);

        intent.putExtra(SCRIPTURE, scripture);

        startActivity(intent);
    }

    public void save(View view) {

        String book = getStringFromEditText(R.id.bookEditText);
        String chapter = getStringFromEditText(R.id.chapterEditText);
        String verse = getStringFromEditText(R.id.verseEditText);

        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(getString(R.string.preferences_book), book);
        editor.putString(getString(R.string.preferences_chapter), chapter);
        editor.putString(getString(R.string.preferences_verse), verse);

        editor.apply();

        Toast.makeText(this, "Scripture saved", Toast.LENGTH_SHORT).show();
    }

    public void load(View view) {

        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);

        String book = preferences.getString(getString(R.string.preferences_book), BLANK_DEFAULT);
        String chapter = preferences.getString(getString(R.string.preferences_chapter), BLANK_DEFAULT);
        String verse = preferences.getString(getString(R.string.preferences_verse), BLANK_DEFAULT);

        setEditText(R.id.bookEditText, book);
        setEditText(R.id.chapterEditText, chapter);
        setEditText(R.id.verseEditText, verse);
    }

    private String getStringFromEditText(int id) {
        EditText editText = findViewById(id);
        return editText.getText().toString().trim();
    }

    private void setEditText(int id, String text) {
        EditText editText = findViewById(id);
        editText.setText(text);
    }
}