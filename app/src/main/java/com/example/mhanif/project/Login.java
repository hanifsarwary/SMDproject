package com.example.mhanif.project;
import com.example.mhanif.project.Database.User;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.TwitterAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.SessionManager;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static com.google.firebase.auth.FirebaseAuth.*;
import static com.google.firebase.auth.FirebaseAuth.getInstance;


public class Login extends AppCompatActivity {
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    Button button;
    TwitterLoginButton twitterLoginButton;
    private FirebaseAuth mAuth;
    EditText Email,password;
    String phoneNumber;

DatabaseReference databaseReference;

// ...
// Initialize Firebase Auth

    void init(){
    }

    @Override
    //C:\>keytool -exportcert -alias androiddebugkey -keystore ~/.android/debug.keystore | "C:\OpenSSL-Win32\bin\openssl.exe" sha1 -binary | "C:\OpenSSL-Win32\bin\openssl.exe" base64
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig =  new TwitterAuthConfig(
                getString(R.string.com_twitter_sdk_android_COMSUMER_KEY),
                getString(R.string.com_twitter_sdk_android_COMSUMER_SECRET));

        TwitterConfig twitterConfig = new TwitterConfig.Builder(this)
                .twitterAuthConfig(authConfig)
                .build();
        button=findViewById(R.id.button);
        Twitter.initialize(twitterConfig);
        setContentView(R.layout.activity_login);
        Twitter.initialize(this);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        mAuth = getInstance();
        Email=(EditText)findViewById(R.id.emailtxt_login);
        password=(EditText)findViewById(R.id.passwordtxt_login);
        ConstraintLayout cl=(ConstraintLayout)findViewById(R.id.login);
        callbackManager = CallbackManager.Factory.create();
        cl.setBackgroundColor(Color.LTGRAY);
        loginButton = (LoginButton)findViewById(R.id.facebook_login);
        twitterLoginButton=(TwitterLoginButton)findViewById(R.id.login_button);



        setSupportActionBar(toolbar);
        FacebookSdk.sdkInitialize(getApplicationContext());
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        databaseReference= FirebaseDatabase.getInstance().getReference();

        //loginButton.setReadPermissions(Arrays.asList(
          //      "public_profile", "email", "user_birthday", "user_friends","user_status"));
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logInWithReadPermissions(Login.this,Arrays.asList(
                        "public_profile", "email", "user_birthday", "user_friends","user_status"));
                LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        handleFacebookAccessToken(loginResult.getAccessToken());
                        //String key=FirebaseDatabase.getInstance().getReference().child("User").push().getKey();
                       String key=loginResult.getAccessToken().getUserId();
                databaseReference.child("User").child(key).setValue(new User(key,FirebaseAuth.getInstance().getCurrentUser().getEmail()));
                  finish();
                Intent in=new Intent(getApplicationContext(),Verify_phoneNumber.class);
                //        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(in);

                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onError(FacebookException e) {

                    }
                });
            }
        });

        twitterLoginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                endAuthorizeInProgress();
                TwitterSession twitterSession= TwitterCore.getInstance().getSessionManager().getActiveSession();
                handleTwitterSession(twitterSession);
                String key=FirebaseDatabase.getInstance().getReference().child("User").push().getKey();
                databaseReference.child("User").child(FirebaseAuth.getInstance().getCurrentUser().getEmail()).setValue(new User(key,FirebaseAuth.getInstance().getCurrentUser().getEmail()));

                Intent in=new Intent(getApplicationContext(),Verify_phoneNumber.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(in);

            }

            @Override
            public void failure(TwitterException exception) {

            }
        });

if(mAuth.getCurrentUser()!=null){
    startActivity(new Intent(getApplicationContext(), Home.class));
}
     //   PhoneAuthProvider.getInstance().verifyPhoneNumber();
    }
    public void regnow_click(View v){
        Intent in=new Intent(this,Register.class);
        startActivity(in);
    }
    public void loginclick(View v){

        mAuth.signInWithEmailAndPassword(Email.getText().toString(),password.getText().toString());
        String key=FirebaseDatabase.getInstance().getReference().child("User").push().getKey();
        databaseReference.child("User").child(FirebaseAuth.getInstance().getCurrentUser().getEmail()).setValue(new User(key,FirebaseAuth.getInstance().getCurrentUser().getEmail()));
        finish();
        Intent in=new Intent(getApplicationContext(),Verify_phoneNumber.class);
        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(in);


    }







    private void handleFacebookAccessToken(AccessToken token) {


        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();

                        } else {

                        }

                        // ...
                    }
                });
    }

    private void handleTwitterSession(TwitterSession session) {
        //Log.d(TAG, "handleTwitterSession:" + session);

        AuthCredential credential = TwitterAuthProvider.getCredential(
                session.getAuthToken().token,
                session.getAuthToken().secret);

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
          //                  Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
              //              updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                //            Log.w(TAG, "signInWithCredential:failure", task.getException());
                  //          Toast.makeText(TwitterLoginActivity.this, "Authentication failed.",
                    //                Toast.LENGTH_SHORT).show();
                      //      updateUI(null);
                        }

                        // ...
                    }
                });
    }


    private void endAuthorizeInProgress() {
        try {
            TwitterAuthClient twitterAuthClient=new TwitterAuthClient();
            Field authStateField = twitterAuthClient.getClass().getDeclaredField("authState");
            authStateField.setAccessible(true);
            Object authState = authStateField.get(twitterAuthClient);
            Method endAuthorize = authState.getClass().getDeclaredMethod("endAuthorize");
            endAuthorize.invoke(authState);
        } catch (NoSuchFieldException | SecurityException | InvocationTargetException |
                NoSuchMethodException | IllegalAccessException e) {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);


    }
    public void onloginclick(View v){
        EditText email,password;
        email=v.findViewById(R.id.emailtxt_login);
        password=v.findViewById(R.id.passwordtxt_login);
        mAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString());
    }


}
