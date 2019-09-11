package com.example.tewdew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class EnterDetails extends AppCompatActivity {

    EditText mHead ;
    EditText mDescription ;
    EditText mComments ;
    Button mButton ;
   private DatabaseReference mRef ;
   private DatabaseReference mCommentRef ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_details);
        mHead = (EditText)findViewById(R.id.enter_head);
        mDescription = (EditText)findViewById(R.id.enter_description);
        mButton = (Button)findViewById(R.id.enter_button);
        mComments = (EditText)findViewById(R.id.enter_comments);


        mRef = FirebaseDatabase.getInstance().getReference().child("ThisApp") ;
        mCommentRef = FirebaseDatabase.getInstance().getReference().child("Komments");

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Content content = new Content();
                content.setmHeader(mHead.getText().toString());
                content.setmDetails(mDescription.getText().toString());
                content.setmComments(null);
                mRef = mRef.child(mHead.getText().toString());
                mCommentRef = mCommentRef.child(mHead.getText().toString());


                Comment comment = new Comment();
                comment.setmComment(mComments.getText().toString());

                mCommentRef.push().setValue(comment).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(),"Sucess",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_LONG).show();
                        }
                    }
                });

                mRef.setValue(content).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful())
                        {
                            startActivity(new Intent(EnterDetails.this,MainActivity.class));
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_LONG).show();
                        }


                    }
                });



            }
        });

    }
}
