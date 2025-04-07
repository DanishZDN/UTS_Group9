package com.android.example.uts_finalgroup9;

import android.graphics.BitmapFactory;
import android.view.*;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.List;

public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.ViewHolder> {
    private final List<File> images;

    public MediaAdapter(List<File> images) {
        this.images = images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                         int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,
                                 int position) {
        File imageFile = images.get(position);
        holder.imageView.setImageBitmap(BitmapFactory.decodeFile(imageFile.getAbsolutePath()));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageItem);
        }
    }
}
