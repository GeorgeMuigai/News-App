package com.gdev.news;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {

    List<CategoriesModal> categories;

    public CategoriesAdapter(List<CategoriesModal> categories) {
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoriesAdapter.CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoriesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesAdapter.CategoriesViewHolder holder, int position) {
        holder.txt_category.setText(categories.get(position).getCategory());
        Picasso.get().load(categories.get(position).getUrlToImage()).into(holder.img_category);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class CategoriesViewHolder extends RecyclerView.ViewHolder{

        ImageView img_category;
        TextView txt_category;
        public CategoriesViewHolder(@NonNull View itemView) {
            super(itemView);

            img_category = itemView.findViewById(R.id.img_category);
            txt_category = itemView.findViewById(R.id.txt_category);
        }
    }
}
