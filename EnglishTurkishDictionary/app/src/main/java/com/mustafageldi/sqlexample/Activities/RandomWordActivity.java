package com.mustafageldi.sqlexample.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mustafageldi.sqlexample.Database.DataBaseHelper;
import com.mustafageldi.sqlexample.Database.WordsDao;
import com.mustafageldi.sqlexample.Models.Words;
import com.mustafageldi.sqlexample.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;

public class RandomWordActivity extends AppCompatActivity {

    private TextView englishText, countQuestion;
    private Button buttonA,buttonB,buttonC;
    private DataBaseHelper helper;
    private ArrayList<Words> randomList = new ArrayList<>();
    private ArrayList<Words> falseAnswer = new ArrayList<>();
    private HashSet<Words> hashSet = new HashSet<>();
    private ArrayList<Words> buttonsText = new ArrayList<>();
    private int questionCount = 0;
    private int trueCount, falseCount;
    private FloatingActionButton floatingActionClose;
    private Words trueWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_word);

        helper = new DataBaseHelper(this);
        randomList = new WordsDao().getRandom10(helper);

        countQuestion = findViewById(R.id.countQuestion);
        buttonA = findViewById(R.id.buttonA);
        buttonB = findViewById(R.id.buttonB);
        buttonC = findViewById(R.id.buttonC);
        englishText = findViewById(R.id.englishText);
        floatingActionClose = findViewById(R.id.floatingActionClose);

        floatingActionClose.setOnClickListener(view -> {
            Intent intent = new Intent(RandomWordActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        });



        getData();


        buttonA.setOnClickListener(view -> {
            questionCount++;
            controller(buttonA);
            getData();
        });

        buttonB.setOnClickListener(view -> {
            questionCount++;
            controller(buttonB);
            getData();
        });

        buttonC.setOnClickListener(view -> {
            questionCount++;
            controller(buttonC);
            getData();
        });


    }


    public void getData(){

        System.out.println(questionCount);

        if (questionCount == 10){
            //Open the Dialog
            Dialog dialog = new Dialog(RandomWordActivity.this);
            dialog.setContentView(R.layout.dialog_design);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCancelable(false);
            dialog.show();

            ImageView close = dialog.findViewById(R.id.closeImage);
            Button button = dialog.findViewById(R.id.buttonAgain);
            TextView textViewTrue = dialog.findViewById(R.id.textViewTrue);
            TextView textViewFalse = dialog.findViewById(R.id.textViewFalse);

            textViewTrue.setText("TRUE: "+trueCount);
            textViewFalse.setText("FALSE: "+falseCount);

            button.setOnClickListener(view -> {
                questionCount=0;
                trueCount = 0;
                falseCount = 0;
                getData();
                dialog.dismiss();
            });

            close.setOnClickListener(view -> {
                dialog.dismiss();
                Intent intent = new Intent(RandomWordActivity.this,MainActivity.class);
                startActivity(intent);
            });


        }else{

            trueWord = randomList.get(questionCount);
            countQuestion.setText("Question: "+(questionCount+1));

            hashSet.clear();
            falseAnswer.clear();
            buttonsText.clear();

            falseAnswer = new WordsDao().getRandom2(helper,trueWord.getWordId());

            hashSet.add(falseAnswer.get(0));
            hashSet.add(falseAnswer.get(1));
            hashSet.add(trueWord);

            for (Words w : hashSet){
                buttonsText.add(w);
            }

            englishText.setText(trueWord.getEnglish());
            buttonA.setText(buttonsText.get(0).getTurkish());
            buttonB.setText(buttonsText.get(1).getTurkish());
            buttonC.setText(buttonsText.get(2).getTurkish());
        }

    }

    public void controller(Button button){
        String buttonText = button.getText().toString().toLowerCase(Locale.ROOT);
        String answer = trueWord.getTurkish().toLowerCase(Locale.ROOT);

        if (answer.equals(buttonText)){
            trueCount++;
            MediaPlayer.create(RandomWordActivity.this,R.raw.success).start();
        }else{
            falseCount++;
            MediaPlayer.create(RandomWordActivity.this,R.raw.fail).start();
        }

    }




}