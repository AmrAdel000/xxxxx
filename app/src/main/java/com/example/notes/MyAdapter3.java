package com.example.notes;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MyAdapter3 extends RecyclerView.Adapter<MyAdapter3.MyViewHolder3> {

    public static int marty;
    private MyAdapter3.ItemClickListener mItemListener;

    ArrayList<String> images;
    ArrayList<String> imagess;
    ArrayList<String> datelistt;
    String s ;
    Context context;
    SharedPreferences shrd ;
    ArrayList<String> c;

    public MyAdapter3(Context ct, ArrayList<String> array, ArrayList<String> araay2, ArrayList<String> intt, ArrayList<String> datelist, ItemClickListener itemClickListeneer) {

        context = ct;
        images = araay2;
        imagess = array;
        datelistt = datelist ;
        c = intt;
        shrd = context.getSharedPreferences("trans file", Context.MODE_PRIVATE);
        this.mItemListener = itemClickListeneer;
    }

    @NonNull
    @Override
    public MyViewHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.test_row2, parent, false);
        return new MyAdapter3.MyViewHolder3(view);
    }


    @Override
    public void onBindViewHolder(@NonNull @NotNull MyAdapter3.MyViewHolder3 holder, int position) {



        s = shrd .getString("theme_number" , "normal");

        switch (s){
            case "materal":holder.itemView.setBackground(ContextCompat.getDrawable(context, R.drawable.pupmenu)); break;
        }

        int ff = images.size() - position - 1 ;

        holder.mytext.setText(imagess.get(ff));
        holder.mytext2.setText(images.get(ff));
        holder.Date.setText(datelistt.get(ff));

        holder.itemView.setOnClickListener(view -> {

            mItemListener.onItemClick(c.get(ff));//it will get the position of our item in our resycler vew
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

    public class MyViewHolder3 extends RecyclerView.ViewHolder {
        TextView mytext;
        TextView mytext2;
        TextView Date;

        public MyViewHolder3(@NonNull View itemView) {
            super(itemView);

            mytext = itemView.findViewById(R.id.textout);
            mytext2 = itemView.findViewById(R.id.textinn);
            Date = itemView.findViewById(R.id.textView19);

        }
    }
}
