package com.example.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter  extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

     private ItemClickListener mItemListener ;

    ArrayList<String> images;
    ArrayList<String> imagess;
    Context context;
   ArrayList<String>  c;

    public MyAdapter(Context ct, ArrayList<String> toks2, ArrayList<String> toks3, ArrayList<String> toks, ArrayList<String> intt, ItemClickListener itemClickListener) {
        context = ct;
        images = toks;
        imagess = toks3;
        c= intt   ;
        this .mItemListener = itemClickListener ;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row1,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  MyAdapter.MyViewHolder holder, int position) {

            holder.mytext.setText(images.get(position));
            holder.mytext2.setText(imagess.get(position));
            holder.itemView.setOnClickListener(view -> {
            mItemListener.onItemClick(c.get(position));//it will get the position of our item in our resycler vew
        });
        holder.itemView.setOnLongClickListener( view -> {
            mItemListener.onItemClick(c.get(position));
            return true;
                    //Toast.makeText(  MyAdapter.this , "تم الحذف" , Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {


        return images.size() ;
    }

    public interface ItemClickListener {

        void onItemClick(String details);

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        //  ImageView myimage  ;
        TextView mytext;
        TextView mytext2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            //   myimage =itemView.findViewById(R.id.myimageView2);
            mytext = itemView.findViewById(R.id.mytext);
            mytext2 = itemView.findViewById(R.id.textin);

        }

    }

}
