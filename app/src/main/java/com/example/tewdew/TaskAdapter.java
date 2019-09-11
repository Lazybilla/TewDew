package com.example.tewdew;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.PipedOutputStream;
import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {


    Context mContext ;
    ArrayList<Content> mContents ;


    public TaskAdapter(Context context,ArrayList<Content> mList)
    {
        this.mContext = context ;
        this.mContents = mList ;
        //this.listener = mListener ;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.tasks,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.mDetails.setText(mContents.get(position).getmDetails());
        holder.mHeader.setText(mContents.get(position).getmHeader());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,CommentsActivity.class);
                intent.putExtra("H",holder.mHeader.getText().toString());
                intent.putExtra("D",holder.mDetails.getText().toString());
                mContext.startActivity(intent);

            }
        });

    }



    @Override
    public int getItemCount() {
        return mContents.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView mHeader ;
        TextView mDetails ;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            mHeader = (TextView)itemView.findViewById(R.id.header);
            mDetails = (TextView)itemView.findViewById(R.id.details);


        }



    }


}
