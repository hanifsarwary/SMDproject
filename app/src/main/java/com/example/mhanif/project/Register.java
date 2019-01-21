package com.example.mhanif.project;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mhanif.project.Database.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class Register extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth mAuth;
    User user;
    EditText Email;
    EditText username;
    EditText password;
    String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        LinearLayout cl=(LinearLayout) findViewById(R.id.reg);

        cl.setBackgroundColor(Color.LTGRAY);
        firebaseAuth=FirebaseAuth.getInstance();

        user=new User();
         Email=(EditText)findViewById(R.id.emailtxt);
        username=(EditText)findViewById(R.id.usernametxt);
        password=(EditText)findViewById(R.id.passwordtxt);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }



    public void onclickreg(View v){
        mAuth=FirebaseAuth.getInstance();

        user.Email=Email.getText().toString().trim();
        user.user_name=username.getText().toString().trim();
        user.password=password.getText().toString().trim();
        firebaseAuth.createUserWithEmailAndPassword(user.Email, user.password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task){

                        //checking if success
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"successful",Toast.LENGTH_LONG).show();
                            firebaseAuth.signInWithEmailAndPassword(user.Email,user.password);
                            String key=FirebaseDatabase.getInstance().getReference().child("User").push().getKey();
                            FirebaseDatabase.getInstance().getReference().child("User").child(key).setValue(new User(key,user.Email));

                            finish();
                            startActivity(new Intent (getApplicationContext(),Verify_phoneNumber.class));
                            //display some message here
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"failed"+
                                    task.getException(),Toast.LENGTH_LONG).show();


                        }



                    }

                });

    }


}
