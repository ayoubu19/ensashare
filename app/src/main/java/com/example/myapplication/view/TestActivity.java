package com.example.myapplication.view;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class TestActivity extends AppCompatActivity {
private FirebaseAuth userAuth ;
private FirebaseUser currentUser ;
private CircleImageView img ;
private TextView username ;
private DatabaseReference databaseReference ;
private void init(){
    userAuth = FirebaseAuth.getInstance();
    currentUser = userAuth.getCurrentUser();
    img =(CircleImageView ) findViewById(R.id.imageview);
    username = (TextView)findViewById(R.id.usernamo);
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        init();
        databaseReference= FirebaseDatabase.getInstance().getReference("students");
        Query query = databaseReference.orderByChild("userName")
                                       .equalTo(currentUser.getDisplayName());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Toast.makeText(getApplicationContext(),"student",
                            Toast.LENGTH_SHORT).show();


                }
                else{
                    Toast.makeText(getApplicationContext(),"teacher",
                            Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Glide.with(this).load(currentUser.getPhotoUrl()).into(img);
        username.setText(currentUser.getDisplayName());
    }
}
