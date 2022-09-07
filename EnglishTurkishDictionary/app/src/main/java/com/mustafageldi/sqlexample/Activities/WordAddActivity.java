package com.mustafageldi.sqlexample.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.mustafageldi.sqlexample.Database.DataBaseHelper;
import com.mustafageldi.sqlexample.Database.WordsDao;
import com.mustafageldi.sqlexample.R;

public class WordAddActivity extends AppCompatActivity {

    private TextInputEditText inputEnglish,inputTurkish;
    private Button saveButton;
    private DataBaseHelper helper;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_add);

        helper = new DataBaseHelper(this);

        saveButton = findViewById(R.id.buttonSave);
        inputEnglish = findViewById(R.id.inputEnglish);
        inputTurkish = findViewById(R.id.inputTurkish);

        saveButton.setOnClickListener(view -> {
            new WordsDao().addWord(helper,inputEnglish.getText().toString(),inputTurkish.getText().toString());
            MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.success);
            mediaPlayer.start();
            Intent intent = new Intent(WordAddActivity.this,MainActivity.class);
            startActivity(intent);
            Toast.makeText(WordAddActivity.this,"Kelime eklendi.",Toast.LENGTH_LONG).show();
        });
    }
}