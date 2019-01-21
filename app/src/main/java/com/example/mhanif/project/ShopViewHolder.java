package com.example.mhanif.project;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by M.hanif on 4/24/2018.
 */

public class ShopViewHolder extends RecyclerView.ViewHolder {
TextView text1,text2;
    public ShopViewHolder(View itemView) {
        super(itemView);
        text1=itemView.findViewById(R.id.s_name);
        text2=itemView.findViewById(R.id.s_contact);
    }
}
