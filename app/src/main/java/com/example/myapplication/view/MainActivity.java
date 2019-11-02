package com.example.myapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    // Buttons **********************
    private TextView usernmae ;
    private TextView password ;
    private Button signIn;
    private Button signup1;
    private ProgressBar loginProgress ;
    private FirebaseAuth userAuth ;
    private Intent i;

    private void init(){

    usernmae = (TextView)findViewById(R.id.username);
    password=(TextView)findViewById(R.id.password);
    signIn=(Button) findViewById(R.id.signIn);
    loginProgress =(ProgressBar)findViewById(R.id.loginProgress);
    loginProgress.setVisibility(View.INVISIBLE);
    signup1 = (Button) findViewById(R.id.signUp);
    userAuth=FirebaseAuth.getInstance();
    i=new Intent(getApplicationContext() ,TestActivity.class);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        //BUTTONS EVENTS *********************
       signIn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
             String email = usernmae.getText().toString()+"@ensashare.com";
             String pass = password.getText().toString();
               if (email.isEmpty()||pass.isEmpty()){
                   Toast.makeText(getApplicationContext(), "verify all fields",
                           Toast.LENGTH_SHORT).show();
               }else{
                   signIn(email,pass);
               }
           }
       });
        signup1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               pop_up_signup.showPopUpsignup(MainActivity.this);
            }

        });

    }
    public void signIn(String email , String pass){

     userAuth.signInWithEmailAndPassword(email , pass).addOnCompleteListener(
             new OnCompleteListener<AuthResult>() {
         @Override
         public void onComplete(@NonNull Task<AuthResult> task) {

             if (task.isSuccessful()){

                 loginProgress.setVisibility(View.VISIBLE);
                 signIn.setActivated(false);
                FirebaseUser user = userAuth.getCurrentUser();

                 updateUI();

             }else{
                 Toast.makeText(getApplicationContext(),task.getException().getMessage(),
                         Toast.LENGTH_SHORT).show();

             }

         }
     });

    }

    public void updateUI(){
   startActivity(i);
   finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = userAuth.getCurrentUser();
        if (user!=null){
           // updateUI();
        }
    }
}