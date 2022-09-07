package com.mustafageldi.sqlexample.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mustafageldi.sqlexample.Adapter.WordsAdapter;
import com.mustafageldi.sqlexample.Database.DataBaseHelper;
import com.mustafageldi.sqlexample.Database.DatabaseCopyHelper;
import com.mustafageldi.sqlexample.Database.WordsDao;
import com.mustafageldi.sqlexample.Models.Words;
import com.mustafageldi.sqlexample.R;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private WordsAdapter wordsAdapter;
    ArrayList<Words> wordsList;
    DataBaseHelper helper;
    FloatingActionButton addButton;
    FloatingActionButton questionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        toolbar = findViewById(R.id.toolbar);
        addButton = findViewById(R.id.floatingActionButton);
        questionButton = findViewById(R.id.floatingActionQuestion);

        questionButton.setOnClickListener(view -> {
            Intent intent = new Intent(this,RandomWordActivity.class);
            startActivity(intent);
        });

        addButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,WordAddActivity.class);
            startActivity(intent);
        });

        dataBaseCopy();

        helper = new DataBaseHelper(this);


        wordsList = new WordsDao().getAllWords(helper);

        wordsAdapter = new WordsAdapter(this,wordsList);

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(wordsAdapter);


        toolbar.setTitle("Dictionary");
        toolbar.setSubtitle("My Words");
        setSupportActionBar(toolbar);
    }


    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(MainActivity.this);
        searchView.setQueryHint("Search Word");
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        doSearch(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        doSearch(newText);
        return false;
    }

    public void dataBaseCopy(){
        DatabaseCopyHelper copyHelper = new DatabaseCopyHelper(this);
        try {
            copyHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        copyHelper.openDataBase();
    }


    public void doSearch(String searchString){
        ArrayList<Words> searchedList = new WordsDao().searchWords(helper,searchString);
        wordsAdapter = new WordsAdapter(MainActivity.this,searchedList);
        recyclerView.setAdapter(wordsAdapter);
    }


}