package com.example.tewdew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity  {

    private DatabaseReference mRef ;
    private RecyclerView mRecycleView ;
    private TaskAdapter mAdapter ;
    private ArrayList<Content> list ;
    private FloatingActionButton mAddTask ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAddTask = (FloatingActionButton)findViewById(R.id.add_task);
        mRecycleView = (RecyclerView)findViewById(R.id.to_do_List);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecycleView.getContext(),DividerItemDecoration.VERTICAL);
        mRecycleView.addItemDecoration(dividerItemDecoration);
        list = new ArrayList<Content>();



        mAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,EnterDetails.class));
            }
        });


        mRef = FirebaseDatabase.getInstance().getReference().child("ThisApp") ;
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    Content content = dataSnapshot1.getValue(Content.class);
                    list.add(content);
                }

                mAdapter = new TaskAdapter(MainActivity.this,list);
                mRecycleView.setAdapter(mAdapter);
                mRecycleView.setClipToPadding(true);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getApplicationContext(),"Empty Lists",Toast.LENGTH_LONG).show();

            }
        });


    }


}
