package com.mustafageldi.sqlexample.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.mustafageldi.sqlexample.Models.Words;
import com.mustafageldi.sqlexample.R;

public class DetailsWord extends AppCompatActivity {

    TextView detailsEnglish,detailsTurkish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_word);

        detailsEnglish = findViewById(R.id.detailsEnglish);
        detailsTurkish = findViewById(R.id.detailsTurkish);

        Words word = (Words) getIntent().getSerializableExtra("word");
        detailsTurkish.setText(word.getTurkish());
        detailsEnglish.setText(word.getEnglish());
    }
}