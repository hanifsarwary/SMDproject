package com.example.mhanif.project;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.mhanif.project.Database.Category;

import java.util.ArrayList;

/**
 * Created by M.hanif on 4/22/2018.
 */

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeViewHolder> {

    ArrayList<Category>categories;
    int home_layout;

    public HomeRecyclerViewAdapter(ArrayList<Category>c,int l){
        categories=c;
        home_layout=l;
    }
    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(home_layout,parent,false);

        return new HomeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {
        holder.text2.setText(categories.get(position).c_desc);
        holder.text1.setText(categories.get(position).c_name);
        holder.image.setImageResource(categories.get(position).picture);
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return categories.size();
    }
}
