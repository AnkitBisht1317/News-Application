package com.ab.newsapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    ArrayList<Model> modelArrayList;
    private int lastPosition = -1;

    public Adapter(Context context, ArrayList<Model> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Model newsItem = modelArrayList.get(position);

        if (newsItem != null) {
            holder.headlines.setText(newsItem.getTitle());
            holder.mainnews.setText(newsItem.getDescription());
            holder.author.setText(newsItem.getAuthor());
            holder.published.setText(newsItem.getPublishedAt());

            // Load image safely
            if (newsItem.getUrlToImage() != null && !newsItem.getUrlToImage().isEmpty()) {
                Glide.with(context).load(newsItem.getUrlToImage()).into(holder.imageView);
            }

            setAnimation(holder.itemView, position);

            holder.itemView.setOnClickListener(view -> {
                int currentPosition = holder.getAdapterPosition();
                if (currentPosition != RecyclerView.NO_POSITION) {
                    Intent intent = new Intent(context, ReadNewsActivity.class);
                    intent.putExtra("URL", modelArrayList.get(currentPosition).getUrl());
                    context.startActivity(intent);
                }
            });
        }
    }

    public void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_in);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView headlines, mainnews, author, published;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            headlines = itemView.findViewById(R.id.mainHeadline_id);
            mainnews = itemView.findViewById(R.id.newsdescription_id);
            author = itemView.findViewById(R.id.author_id);
            published = itemView.findViewById(R.id.published_id);
            imageView = itemView.findViewById(R.id.news_image);
        }
    }
}
