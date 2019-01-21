package com.example.mhanif.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.mhanif.project.Database.Category;
import com.facebook.login.LoginManager;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;

import java.util.ArrayList;

public class Home extends AppCompatActivity implements RecyclerView.OnItemTouchListener{
    ArrayList<Category>categoryArrayList;
    RecyclerView rv;
    HomeRecyclerViewAdapter homeRecyclerViewAdapter;
    GestureDetector ges;
    private DatabaseReference mDatabase;
    AdView mAdView;
// ...


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        MobileAds.initialize(this,"ca-app-pub-8188077984704181~6896162875");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        categoryArrayList=new ArrayList<Category>();
        categoryArrayList.add(new Category(1,"Phones","find best phones here",R.drawable.phones));
        categoryArrayList.add(new Category(2,"Fashion","best fashion collection for men",R.drawable.fashion));
        categoryArrayList.add(new Category(5,"Homes","search your living here",R.drawable.homes));
        categoryArrayList.add(new Category(6,"Sports","Play with us",R.drawable.men));
        categoryArrayList.add(new Category(7,"Computers","Coputers are the nw sexy",R.drawable.computers));
        categoryArrayList.add(new Category(8,"Food","eat well grow fine",R.drawable.food));
        homeRecyclerViewAdapter=new HomeRecyclerViewAdapter(categoryArrayList,R.layout.home_row);
        rv=findViewById(R.id.hrcv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(homeRecyclerViewAdapter);
        saveCategoryOnline();
        ges=new GestureDetector(this,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e){
                View child= rv.findChildViewUnder(e.getX(),e.getY());
                if(child!=null){
                    Intent in=new Intent(getApplicationContext(),ItemAndShop.class);
                    int index=rv.getChildAdapterPosition(child);
                    in.putExtra("category_name",categoryArrayList.get(index).c_name);
                    startActivity(in);




                }


                return true;
            }
        });
        rv.addOnItemTouchListener(this);
    }



    public void saveCategoryOnline(){
        for(int i=0;i<categoryArrayList.size();i++)
        {
            mDatabase.child("Category").child(categoryArrayList.get(i).c_name).setValue(categoryArrayList.get(i));
        }

    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.home_option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        // Handle item selection

        {
           if(R.id.logout==menuItem.getItemId()){
                Toast.makeText(getApplicationContext(),"logging out.....",Toast.LENGTH_LONG);
                FirebaseAuth.getInstance().signOut();
               LoginManager.getInstance().logOut();

                startActivity(new Intent(getApplicationContext(),Login.class));
                return true;
           }
            else if(menuItem.getItemId()== R.id.profile){
                startActivity(new Intent(getApplicationContext(),profile.class));
                return true;
           }
           else if(menuItem.getItemId()==R.id.sensor)
           {
               startActivity(new Intent(getApplicationContext(),SensorTest.class));
               return true;
           }
           else if(menuItem.getItemId()==R.id.sensortest1){
                startActivity(new Intent(getApplicationContext(),SensorTest1.class));
                return true;
           }
           else if(menuItem.getItemId()==R.id.search){
               SearchView searchView=findViewById(R.id.homesearch);
               searchView.setVisibility(Button.VISIBLE);
               return true;
           }
           else
             return super.onOptionsItemSelected(menuItem);
        }



    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        ges.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
