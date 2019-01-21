package com.example.mhanif.project;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.mhanif.project.Database.Item;

/**
 * Created by M.hanif on 4/24/2018.
 */

public class ItemViewHolder extends RecyclerView.ViewHolder {
    TextView text1,text2;
    public ItemViewHolder(View itemView) {
        super(itemView);
        text1=itemView.findViewById(R.id.i_name);
        text2=itemView.findViewById(R.id.i_description);
    }
}
