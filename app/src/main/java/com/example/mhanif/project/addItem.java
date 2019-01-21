package com.example.mhanif.project;

import android.arch.persistence.room.Database;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mhanif.project.Database.Category;
import com.example.mhanif.project.Database.Item;
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
import java.util.ArrayList;

public class addItem extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{

    EditText name,price,desc;
    Spinner spinner;
    ArrayList<String> sp_data;
    Bitmap bitmap;
    private Button btnChoose, btnUpload;
    private ImageView imageView;
    DatabaseReference databaseReference;
    private Uri filePath;

    private final int PICK_IMAGE_REQUEST = 71;
    String spinner_selected;
    ArrayAdapter<String> sp_adapter;
    DatabaseReference mdatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        spinner=(Spinner) findViewById(R.id.item_category);
        sp_data=new ArrayList<String>();
        sp_adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,sp_data);
        spinner.setAdapter(sp_adapter);
        spinner.setOnItemSelectedListener(this);
        mdatabase= FirebaseDatabase.getInstance().getReference().child("Category");
        btnChoose=(Button)findViewById(R.id.add_img);
        imageView=(ImageView)findViewById(R.id.item_img);
        name=findViewById(R.id.item_name);
        price=findViewById(R.id.item_price);
        desc=findViewById(R.id.item_description);
        bitmap=null;
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });


    }
    public void saveItemOnline(){
    databaseReference=FirebaseDatabase.getInstance().getReference();
    String key=databaseReference.child("Item").push().getKey();
    String seller= FirebaseAuth.getInstance().getCurrentUser().getEmail();
    String itemname=name.getText().toString();
    float item_price=Float.parseFloat(price.getText().toString());
    String itemDescription=desc.getText().toString();
    databaseReference.child("Item").child(key).setValue(new Item(seller,itemname,key,spinner_selected,itemDescription,item_price));

    if(filePath!=null){
        StorageReference storageReference= FirebaseStorage.getInstance().getReference().child("Image").child(key);

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
    }
    public void onDoneClick(View v){
        saveItemOnline();
        finish();
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
               bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener
        mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous artist list
                sp_data.clear();


                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    Category category = postSnapshot.getValue(Category.class);
                    //adding artist to the list
                    sp_data.add(category.c_name);
                    System.out.println(category.c_name);
                    sp_adapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        spinner_selected=(String) adapterView.getItemAtPosition(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
