package com.example.mhanif.project;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.mhanif.project.Database.Category;
import com.example.mhanif.project.Database.Item;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment implements View.OnClickListener,RecyclerView.OnItemTouchListener{
    ArrayList<Item> itemArrayList;
    RecyclerView rv;
    ItemRecyclerViewAdapter itemRecyclerViewAdapter;
    Button button;
    GestureDetector ges;
    public BlankFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_blank,container,false);
        rv=v.findViewById(R.id.frag1_rcv);
        itemArrayList=new ArrayList<Item>();
        itemRecyclerViewAdapter=new ItemRecyclerViewAdapter(itemArrayList,R.layout.item_row) ;
        DatabaseReference mdatabase=FirebaseDatabase.getInstance().getReference().child("Item");
        rv.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                //Toast.makeText(getContext(),"hanif",Toast.LENGTH_SHORT).show();
                View child= rv.findChildViewUnder(e.getX(),e.getY());
                if(child!=null){
                    Intent in=new Intent(getContext(),view_item.class);
                    int index=rv.getChildAdapterPosition(child);
                    Toast.makeText(getContext(),"Item you selected is"+itemArrayList.get(index).item_name,Toast.LENGTH_LONG).show();
                    Toast.makeText(getContext(),""+itemArrayList.get(index).price,Toast.LENGTH_LONG).show();
                     startActivity(in);




                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                Toast.makeText(getContext(),"hanif",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });


        mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous artist list
                itemArrayList.clear();

                String c=getArguments().getString("category_name");
                Toast.makeText(getContext(),c,Toast.LENGTH_LONG).show();
                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    System.out.print(1);
                    Item item = postSnapshot.getValue(Item.class);
                    //adding artist to the list
                    if(item.cat.equals(c))
                        itemArrayList.add(item);

                    itemRecyclerViewAdapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


            }
        });


        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(itemRecyclerViewAdapter);
        button=(Button)v.findViewById(R.id.add_item);
        button.setOnClickListener(this);


        return v;
    }


    @Override
    public void onClick(View view) {
        startActivity(new Intent(getContext(),addItem.class));
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
