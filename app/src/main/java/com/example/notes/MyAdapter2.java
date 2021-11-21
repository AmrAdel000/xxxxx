package com.example.notes;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.MyViewHolder2> {
    public static int marty;
    private MyAdapter.ItemClickListener mItemListener;

    ArrayList<String> images;
    ArrayList<String> imagess;
    String s ;
    Context context;
    SharedPreferences shrd ;
    ArrayList<String> c;

    public MyAdapter2(Context ct, ArrayList<String> array, ArrayList<String> araay2, ArrayList<String> intt, MyAdapter.ItemClickListener itemClickListener) {

        context = ct;
        images = araay2;
        imagess = array;
        c = intt;
        shrd = context.getSharedPreferences("trans file", Context.MODE_PRIVATE);
        this.mItemListener = itemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row2, parent, false);
        return new MyAdapter2.MyViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder2 holder, int position) {


        s = shrd .getString("theme_number" , "normal");

        switch (s){
            case "materal":holder.itemView.setBackground(ContextCompat.getDrawable(context, R.drawable.pupmenu)); break;
        }



        holder.mytext.setText(imagess.get(position));
        holder.mytext2.setText(images.get(position));
        holder.itemView.setOnClickListener(view -> {

     mItemListener.onItemClick(c.get(position) , images.get(position));//it will get the position of our item in our resycler vew



        });
        ///////////////////

        String fullName = mergeNames("amr", "adel");
    }

    public String mergeNames(String first, String second) {
        return first + " " + second;
    }

    ///////////////
    @Override
    public int getItemCount() {
        return imagess.size();
    }

    public interface ItemClickListener {

        void onItemClick(String details);

    }

    public class MyViewHolder2 extends RecyclerView.ViewHolder {
        TextView mytext;
        TextView mytext2;

        public MyViewHolder2(@NonNull View itemView) {
            super(itemView);

            mytext = itemView.findViewById(R.id.textout);
            mytext2 = itemView.findViewById(R.id.textinn);
        }
    }
}
