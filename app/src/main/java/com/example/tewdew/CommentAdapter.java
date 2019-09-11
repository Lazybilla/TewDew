package com.example.tewdew;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {


   private Context mContext ;
   private ArrayList<Comment> mList ;


   public CommentAdapter(Context context,ArrayList<Comment> list)
   {

       this.mContext = context ;
       this.mList = list ;


   }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CommentViewHolder(LayoutInflater.from(mContext).inflate(R.layout.comments,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
       holder.mComment.setText(mList.get(position).getmComment());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class CommentViewHolder extends RecyclerView.ViewHolder
    {

        TextView mComment ;
        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            mComment = (TextView)itemView.findViewById(R.id.comments_textView);
        }

    }



}
