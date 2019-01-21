package com.example.mhanif.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.mhanif.project.Database.Shop;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addShop extends AppCompatActivity {
    EditText sName,sPhone,sAddress;
    String longi,lati;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shop);
        sName=(EditText)findViewById(R.id.shop_name);
        sPhone=(EditText)findViewById(R.id.shop_phone);
        sAddress=(EditText)findViewById(R.id.shop_address);
    }

    public void onAddLocationClick(View v){
        startActivityForResult(new Intent(getApplicationContext(),Map.class),1);

    }

    @Override
    protected void onActivityResult(int requestcode,int resultcode,Intent data){
        Bundle bundle=data.getExtras();
        longi=bundle.getString("Longitude");
        lati=bundle.getString("Latitude");


    }
    public void onSaveClick(View v){
      databaseReference= FirebaseDatabase.getInstance().getReference().child("Shop");
      String sid=databaseReference.push().getKey();

      databaseReference.child(sid).setValue(new Shop(sid,
              sName.getText().toString()
              ,longi
              ,lati,
              sPhone.getText().toString(),
              sAddress.getText().toString(),
              FirebaseAuth.getInstance().getCurrentUser().getEmail()
              ));
      finish();

    }



}
