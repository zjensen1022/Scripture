package com.example.scripture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ViewScripture extends AppCompatActivity {

    private static final String TAG = "ViewScripture";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_scripture);

        Intent intent = getIntent();

        String scripture = intent.getStringExtra(MainActivity.SCRIPTURE);

        Log.d(TAG, "Received intent with " + scripture);

        TextView scriptureView = findViewById(R.id.scriptureView);

        scriptureView.setText(scripture);
    }
}