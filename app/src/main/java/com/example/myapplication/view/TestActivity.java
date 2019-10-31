package com.example.myapplication.view;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;

public class TestActivity extends AppCompatActivity {
private FirebaseAuth userAuth ;
private FirebaseUser currentUser ;
private CircleImageView img ;
private TextView username ;
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
        Glide.with(this).load(currentUser.getPhotoUrl()).into(img);
        username.setText(currentUser.getDisplayName());
    }
}
