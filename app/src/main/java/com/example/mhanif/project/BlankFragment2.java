package com.example.mhanif.project;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.mhanif.project.Database.Shop;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment2 extends Fragment implements View.OnClickListener {


    ArrayList<Shop> shopArrayList;
    RecyclerView rv;
    ShopRecyclerViewAdapter shopRecyclerViewAdapter;
    Button button;

    public BlankFragment2() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,
                            @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_blank_fragment2, container, false);
        rv=v.findViewById(R.id.frag2_rcv);
        shopArrayList=new ArrayList<Shop>();
        DatabaseReference mdatabase= FirebaseDatabase.getInstance().getReference().child("Shop");

        mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous artist list
                shopArrayList.clear();

                Shop shop;
                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    //getting Shop
                   shop=new Shop();

                    {
                      //  shop.shop_name=postSnapshot.child("Shop_name").getValue(String.class);

                        shop = postSnapshot.getValue(Shop.class);
                        shopArrayList.add(shop);
                    }
                    //adding artist to the list


                    shopRecyclerViewAdapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


            }
        });
        shopRecyclerViewAdapter=new ShopRecyclerViewAdapter(shopArrayList,R.layout.shop_row) ;

        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(shopRecyclerViewAdapter);
         button=(Button)v.findViewById(R.id.add_shop);
        button.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(getContext(),addShop.class));

    }
}
