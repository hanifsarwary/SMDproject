package com.example.mhanif.project;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.mhanif.project.Database.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;

public class profile extends AppCompatActivity implements TextToSpeech.OnInitListener, TextToSpeech.OnUtteranceCompletedListener{

    EditText first_name,last_name,phone,address;
    String Email;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    private Uri filePath;
    ImageView imageView;
    Button speech;
    TextToSpeech textToSpeech;
    String userkey;

    private final int PICK_IMAGE_REQUEST = 71;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        first_name=findViewById(R.id.u_fname);
        last_name=findViewById(R.id.u_lname);
        phone=findViewById(R.id.u_phone);
        address=(EditText) findViewById(R.id.u_address);
        imageView=findViewById(R.id.user_img);
        Email= FirebaseAuth.getInstance().getCurrentUser().getEmail();
        databaseReference=FirebaseDatabase.getInstance().getReference();

        speech=findViewById(R.id.speech);
        textToSpeech=new TextToSpeech(this,this);
        speech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!textToSpeech.isSpeaking()){
                    HashMap<String,String> stringStringHashMap = new HashMap<String, String>();
                    stringStringHashMap.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID,"Hello how are you");
                    textToSpeech.speak(first_name.getText().toString(),TextToSpeech.QUEUE_ADD,stringStringHashMap);
                    textToSpeech.speak(last_name.getText().toString(),TextToSpeech.QUEUE_ADD,stringStringHashMap);
                    speech.setVisibility(Button.GONE);
                }else{
                    textToSpeech.stop();
                }
            }
        });


        databaseReference=FirebaseDatabase.getInstance().getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.print("hdjdh kgjgakjfgakjyfgf\n");
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    User user = (User) postSnapshot.getValue(User.class);
                    if((user.Email!= null) && (Email!=null)) {
                        if (user.Email.equals(Email))

                        {
                            userkey = user.key;
                            first_name.setText(user.firstName);
                            last_name.setText(user.lastName);
                            phone.setText(user.phone);
                            address.setText(user.address);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }
    @Override
    public void onStart() {

        super.onStart();

        databaseReference=FirebaseDatabase.getInstance().getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.print("hdjdh kgjgakjfgakjyfgf\n");
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    User user = (User) postSnapshot.getValue(User.class);
                    if((user.Email!= null) && (Email!=null)) {
                        if (user.Email.equals(Email))

                        {
                            userkey = user.key;
                            first_name.setText(user.firstName);
                            last_name.setText(user.lastName);
                            phone.setText(user.phone);
                            address.setText(user.address);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public void onSaveClick(View v){

        //String key=databaseReference.child("Item").push().getKey();
        databaseReference=FirebaseDatabase.getInstance().getReference();
        String fname=first_name.getText().toString();
        String lname=last_name.getText().toString();
        String ph=phone.getText().toString();
        String add=address.getText().toString();
        if(userkey==null)
             userkey=FirebaseDatabase.getInstance().getReference().child("User").push().getKey();

        databaseReference.child("User").child(userkey).setValue(new User(fname,lname,Email,
                FirebaseAuth.getInstance().getCurrentUser().getUid()
                ,ph,add,userkey));

        if(filePath!=null){
             storageReference= FirebaseStorage.getInstance().getReference().child("Image").child("User").child(Email);

            storageReference.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Toast.makeText(getApplicationContext(), "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(getApplicationContext(), "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());

                        }
                    });

        }
        Toast.makeText(getApplicationContext(),"profile saved",Toast.LENGTH_LONG).show();
    }
    public void onAddImageClick(View v){
        chooseImage();
    }


    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
               Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onInit(int i) {
        textToSpeech.setOnUtteranceCompletedListener(this);
    }

    @Override
    public void onUtteranceCompleted(String s) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),"Utterence completed",Toast.LENGTH_LONG).show();
                Button button = (Button) findViewById(R.id.speech);
                button.setVisibility(Button.VISIBLE);
            }
        });{

        }
    }
}
