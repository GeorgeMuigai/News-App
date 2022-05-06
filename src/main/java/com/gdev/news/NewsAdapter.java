package com.gdev.news;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    List<Articles> newsList;

    public NewsAdapter(List<Articles> newsList) {
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Articles articles = newsList.get(position);
        Picasso.get().load(newsList.get(position).getUrlToImage()).into(holder.img_news);
        holder.txt_news_heading.setText(newsList.get(position).getTitle());
        holder.txt_news_description.setText(newsList.get(position).getDescription());

        //
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), NewsDetails.class);
                intent.putExtra("title", articles.getTitle());
                intent.putExtra("content", articles.getContent());
                intent.putExtra("image", articles.getUrlToImage());
                intent.putExtra("url", articles.getUrl());
                intent.putExtra("desc", articles.getDescription());

                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{

        ImageView img_news;
        TextView txt_news_heading, txt_news_description;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            img_news = itemView.findViewById(R.id.img_news);
            txt_news_heading = itemView.findViewById(R.id.txt_news_heading);
            txt_news_description = itemView.findViewById(R.id.txt_news_description);

        }
    }
}
