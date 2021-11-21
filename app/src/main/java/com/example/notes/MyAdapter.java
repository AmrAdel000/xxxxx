package com.example.notes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter  extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

     private ItemClickListener mItemListener ;

    ArrayList<String> images;
    ArrayList<String> imagess;
    ArrayList<String> lockk;
    Context context;
    ArrayList<String>  c;
    String s ;
    String lock ;
   SharedPreferences shrd ;
   SharedPreferences shrd2 ;
    public MyAdapter(Context ct, ArrayList<String> toks2, ArrayList<String> toks3, ArrayList<String> toks, ArrayList<String> intt, ArrayList<String> lock, ItemClickListener itemClickListener) {
        context = ct;
        images = toks;
        imagess = toks3;
        c= intt   ;
        lockk = lock;
        shrd = context.getSharedPreferences("trans file", Context.MODE_PRIVATE);
        shrd2 = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        this .mItemListener = itemClickListener ;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        if(shrd2.getString("notes_item_size" , "small").equals("small")) {
            View view = inflater.inflate(R.layout.row1, parent, false);
            return new MyViewHolder(view);
        }else {
            View view = inflater.inflate(R.layout.test_row1, parent, false);
            return new MyViewHolder(view);
        }
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull  MyAdapter.MyViewHolder holder, int position) {

        s = shrd .getString("theme_number" , "normal");

        switch (s){
            case "one"    :holder.itemView.setBackgroundColor(Color.parseColor("#7E91AF")); break;
            case "tow"    :holder.itemView.setBackgroundColor(Color.parseColor("#141E37")); break;
            case "three"  :holder.itemView.setBackgroundColor(Color.parseColor("#4563C7")); break;
            case "normal" :holder.itemView.setBackground(ContextCompat.getDrawable(context, R.drawable.itemback)); break;
            case "materal":holder.itemView.setBackground(ContextCompat.getDrawable(context, R.drawable.back2)); break;
            case "image"  :holder.itemView.setBackground(ContextCompat.getDrawable(context, R.drawable.itemback3));break;
                //holder.mytext.setTextColor(ContextCompat.getColor(context , R.color.black));
        }
        int ff= images.size() - position - 1 ;

        if (lockk.get(ff).equals("yes")){

            holder.mytext.setText(images.get(ff));
           // تم اقفال هذه الملاحظه
            holder.mytext2.setText(  "\n         ///////////////////////");
            holder.itemView.setBackground(ContextCompat.getDrawable(context, R.drawable.lock));
        }else {

            // بدل ما تحط position حد ff  في كله
            holder.mytext.setText(images.get(ff));
            holder.mytext2.setText(imagess.get(ff));
        }

         //   holder.mytext.setTextColor(Color.parseColor("#7E91AF"));
         //   holder.itemView.setBackgroundColor(Color.parseColor(v));
            holder.itemView.setOnClickListener(view -> {
            mItemListener.onItemClick(c.get(ff) , lockk.get(ff));//it will get the position of our item in our resycler vew
        });
        holder.itemView.setOnLongClickListener( view -> {
            mItemListener.onItemClick(c.get(ff) , lockk.get(ff));
            return true;
                    //Toast.makeText(  MyAdapter.this , "تم الحذف" , Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {

        return images.size() ;
    }

    public interface ItemClickListener {
        void onItemClick(String details , String lock);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mytext;
        TextView mytext2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mytext = itemView.findViewById(R.id.mytext);
            mytext2 = itemView.findViewById(R.id.textin);

        }
    }
}
