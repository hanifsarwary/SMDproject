package com.example.mhanif.project;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mhanif.project.Database.Item;
import com.example.mhanif.project.Database.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class view_item extends AppCompatActivity {
TextView t1,t2,t3,t4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);
        t1=findViewById(R.id.i_name);
        t2=findViewById(R.id.i_description);
        t3=findViewById(R.id.iprice);
        t4=findViewById(R.id.icontact);



    }
}
