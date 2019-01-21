package com.example.mhanif.project;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mhanif.project.Database.Category;
import com.example.mhanif.project.Database.Item;

import java.util.ArrayList;

/**
 * Created by M.hanif on 4/24/2018.
 */

public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<ItemViewHolder> {
ArrayList<Item> items;int item_layout;

public ItemRecyclerViewAdapter(ArrayList<Item>items,int item_layout){
    this.item_layout=item_layout;
    this.items=items;
}
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(item_layout,parent,false);

        return new ItemViewHolder(v);

    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
            holder.text1.setText(items.get(position).item_name);
            holder.text2.setText(items.get(position).item_discription);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
