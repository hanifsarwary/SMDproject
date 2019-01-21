package com.example.mhanif.project;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mhanif.project.Database.Shop;

import java.util.ArrayList;

/**
 * Created by M.hanif on 4/24/2018.
 */

public class ShopRecyclerViewAdapter extends RecyclerView.Adapter<ShopViewHolder> {
    ArrayList<Shop> shops;
    int shop_layout;
    public ShopRecyclerViewAdapter(ArrayList<Shop>shops,int shop_layout){
        this.shops=shops;
        this.shop_layout=shop_layout;
    }
    @Override
    public ShopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(shop_layout,parent,false);
        return new ShopViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ShopViewHolder holder, int position) {
    holder.text1.setText(shops.get(position).shop_name);
    holder.text2.setText(shops.get(position).address);
    }

    @Override
    public int getItemCount() {
        return shops.size();
    }
}
