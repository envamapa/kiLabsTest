package com.mx.envamapa.app.wundertest.commons.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mx.envamapa.app.wundertest.R;
import com.mx.envamapa.app.wundertest.commons.ImageSize;
import com.mx.envamapa.app.wundertest.commons.Utils;
import com.mx.envamapa.app.wundertest.data.sources.service.respPhotos.Photo;
import com.mx.envamapa.app.wundertest.views.view.mainActivity.RowClickListener;

import java.util.ArrayList;
import java.util.List;

public class PhotoAdapter
        extends RecyclerView.Adapter {

    private static final int VIEW_ITEM = 1;
    private static final int VIEW_PROG = 0;

    private final List<Photo> items = new ArrayList<>();
    private RowClickListener<Photo> rowClickListener;

    @Override
    public int getItemViewType(int position) {
        return items.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_ITEM) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_photo, parent, false);
            return new PhotoViewHolder(itemView);
        } else {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_progress, parent, false);
            return new ProgressViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof PhotoViewHolder) {
            PhotoViewHolder vh = (PhotoViewHolder) holder;
            Photo item = items.get(position);
            Utils.bindImage(item.getImageUrl(ImageSize.MEDIUM), vh.image, true, R.drawable.ic_image_24dp, R.drawable.ic_broken_image_24dp);
            if (rowClickListener != null) {
                vh.image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        rowClickListener.onRowClicked(holder.getAdapterPosition(),
                                items.get(holder.getAdapterPosition()));
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setRowClickListener(RowClickListener<Photo> rowClickListener) {
        this.rowClickListener = rowClickListener;
    }

    public void addAll(List<Photo> newItems) {
        if (newItems == null) {
            items.add(null);
            notifyItemInserted(getItemCount() - 1);
        } else {
            items.addAll(newItems);
            notifyDataSetChanged();
        }
    }

    public List<Photo> getAll() {
        return items;
    }

    public void remove(int index) {
        if (index == -1) return;
        items.remove(index);
        notifyItemRemoved(index);
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

}