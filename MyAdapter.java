package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    ArrayList<String> t1 = new ArrayList<String>();ArrayList<String> t2 = new ArrayList<String>();ArrayList<String> t3 = new ArrayList<String>();ArrayList<String> t4 = new ArrayList<String>();
    Context context;
    public MyAdapter(Context ct, ArrayList<String> d1, ArrayList<String> d2, ArrayList<String> d3, ArrayList<String> d4){
        context = ct;
        t1 = d1;
        t2 = d2;
        t3 = d3;
        t4 = d4;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.wordshett1,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.wordTextEnglish.setText(t1.get(position));
        holder.wordTextTurkish.setText(t2.get(position));
        holder.wordTextType.setText(t3.get(position));
        holder.wordTextLvl.setText(t4.get(position));
        //holder.wordColor.setBackgroundColor(Color.parseColor("#00FF0A"));
        if(t4.get(position).matches("A1")) {holder.wordColor.setBackgroundColor(Color.parseColor("#0384F4"));}
        else if(t4.get(position).matches("A2")) {holder.wordColor.setBackgroundColor(Color.parseColor("#4CAFA0"));}
        else if(t4.get(position).matches("B1")) {holder.wordColor.setBackgroundColor(Color.parseColor("#41F459"));}
        else if(t4.get(position).matches("B2")) {holder.wordColor.setBackgroundColor(Color.parseColor("#FFD500"));}
        else if(t4.get(position).matches("C1")) {holder.wordColor.setBackgroundColor(Color.parseColor("#FF6600"));}
        else if(t4.get(position).matches("C2")) {holder.wordColor.setBackgroundColor(Color.parseColor("#FF0000"));}

        //System.out.println(t4.get(position).matches("A1"));
    }

    @Override
    public int getItemCount() {
        return t2.size()-1;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView wordTextEnglish,wordTextTurkish,wordTextLvl,wordTextType;
        View wordColor;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            wordTextEnglish = itemView.findViewById(R.id.wordTextEnglish);
            wordTextTurkish = itemView.findViewById(R.id.wordTextTurkish);
            wordTextType = itemView.findViewById(R.id.wordTextType);
            wordTextLvl = itemView.findViewById(R.id.wordTextLvl);
            wordColor = itemView.findViewById(R.id.wordColor);
        }
    }
}
