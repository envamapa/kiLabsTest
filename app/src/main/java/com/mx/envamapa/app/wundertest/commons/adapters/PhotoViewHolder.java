package com.mx.envamapa.app.wundertest.commons.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.mx.envamapa.app.wundertest.R;

/**
 * Created by enya on 11/15/18.
 */

public class PhotoViewHolder extends RecyclerView.ViewHolder {

    public ImageView image;

    public PhotoViewHolder(View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.image);
    }

}
