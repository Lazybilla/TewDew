package com.example.tewdew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AddComments extends AppCompatActivity {

   private EditText mComment ;
   private Button mButton ;
   private DatabaseReference mRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comments);
        mComment = (EditText)findViewById(R.id.add_comment_editText);
        mButton = (Button)findViewById(R.id.add_comment_button);

        Intent i = getIntent() ;
     final String s =  i.getStringExtra("K");

       mRef = FirebaseDatabase.getInstance().getReference().child("Komments").child(s);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Comment comment = new Comment();
                comment.setmComment(mComment.getText().toString());
                mRef.push().setValue(comment);
                Intent intent = new Intent(AddComments.this,CommentsActivity.class);
                intent.putExtra("S",s);
                startActivity(intent);


            }
        });


    }
}
