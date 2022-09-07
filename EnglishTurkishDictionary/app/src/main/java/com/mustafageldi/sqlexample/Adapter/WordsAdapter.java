package com.mustafageldi.sqlexample.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.mustafageldi.sqlexample.Activities.DetailsWord;
import com.mustafageldi.sqlexample.Models.Words;
import com.mustafageldi.sqlexample.R;
import java.util.ArrayList;

public class WordsAdapter extends RecyclerView.Adapter<WordsAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Words> wordsArrayList;

    public WordsAdapter(Context mContext, ArrayList<Words> wordsArrayList) {
        this.mContext = mContext;
        this.wordsArrayList = wordsArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_design_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Words word = wordsArrayList.get(position);


        holder.englishTextRecycler.setText(word.getEnglish());
        holder.turkishTextRecycler.setText(word.getTurkish());
        holder.cardViewRow.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, DetailsWord.class);
            intent.putExtra("word",word);
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return wordsArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView englishTextRecycler,turkishTextRecycler;
        public CardView cardViewRow;

        public ViewHolder(@NonNull View view){
            super(view);
            englishTextRecycler = view.findViewById(R.id.englishTextRecycler);
            turkishTextRecycler = view.findViewById(R.id.turkishTextRecycler);
            cardViewRow = view.findViewById(R.id.cardViewRow);
        }

    }



}
