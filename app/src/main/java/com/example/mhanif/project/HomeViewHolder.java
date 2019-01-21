package com.example.mhanif.project;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by M.hanif on 4/22/2018.
 */

public class HomeViewHolder extends RecyclerView.ViewHolder {
public TextView text1,text2;
public ImageView image;
    public HomeViewHolder(View itemView) {
        super(itemView);
        text1=(TextView)itemView.findViewById(R.id.c_name);
        text2=(TextView)itemView.findViewById(R.id.c_description);
        image=(ImageView)itemView.findViewById(R.id.c_image);
    }

}
