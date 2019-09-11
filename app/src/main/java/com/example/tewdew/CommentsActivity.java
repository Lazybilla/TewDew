package com.example.tewdew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseAppLifecycleListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CommentsActivity extends AppCompatActivity {

    FloatingActionButton mButton ;
    private RecyclerView mRecyclerView ;
    private ArrayList<Comment> list ;
    private CommentAdapter mAdapter ;
    private TextView mHeader ;
    private TextView mDesription ;

    private DatabaseReference mRef ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        mRecyclerView = (RecyclerView) findViewById(R.id.comments_lists);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<Comment>();
        mHeader = (TextView)findViewById(R.id.Comments_header);
        mDesription = (TextView)findViewById(R.id.comments_description);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        final Intent intent = getIntent() ;
        final String s,s2 ;
        s = intent.getStringExtra("H");
        s2 = intent.getStringExtra("S");
        final String description = intent.getStringExtra("D");




        mHeader.setText(s);
        mDesription.setText(description);


        mButton = (FloatingActionButton)findViewById(R.id.add_comments);

        if (s==null) {
            mRef = FirebaseDatabase.getInstance().getReference().child("Komments").child(s2);
        }
        else
        {
            mRef = FirebaseDatabase.getInstance().getReference().child("Komments").child(s);
        }


        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    Comment s1 = dataSnapshot1.getValue(Comment.class);
                    list.add(s1);
                }

                mAdapter = new CommentAdapter(CommentsActivity.this,list);

                mRecyclerView.setAdapter(mAdapter);

                mAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(CommentsActivity.this,AddComments.class);
                intent1.putExtra("K",s);
                startActivity(intent1);
            }
        });


    }
}
