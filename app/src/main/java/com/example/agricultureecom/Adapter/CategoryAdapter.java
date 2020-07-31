package com.example.agricultureecom.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.agricultureecom.Models.CategoryModel;
import com.example.agricultureecom.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.viewHolder> {

    Context context;
    List<CategoryModel> catModel;

    public CategoryAdapter(Context context, List<CategoryModel> catModel) {
        this.context = context;
        this.catModel = catModel;
    }

    @NonNull
    @Override
    public CategoryAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_layout, parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.viewHolder holder, int position) {
        holder.txtCategory.setText(catModel.get(position).getCatName());
        Glide.with(context)
                .load(catModel.get(position).getImg())
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return catModel.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView txtCategory;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.roundedimage);
            txtCategory = itemView.findViewById(R.id.category_name);

        }
    }


}
