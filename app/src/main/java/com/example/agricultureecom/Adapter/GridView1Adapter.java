package com.example.agricultureecom.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.agricultureecom.Models.ProductModel;
import com.example.agricultureecom.ProductDetailsActivity;
import com.example.agricultureecom.R;

import java.util.List;

public class GridView1Adapter extends BaseAdapter {

    List<ProductModel> productModelList;

    public GridView1Adapter(List<ProductModel> productModelList) {
        this.productModelList = productModelList;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {

        final View view;
        if(convertView == null){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_view,null);

            view.setElevation(0);
            view.setBackgroundColor(Color.parseColor("#ffffff"));

            ImageView productImage = view.findViewById(R.id.imgProductImage);
            TextView productName = view.findViewById(R.id.txtProductName);
            TextView productDescription = view.findViewById(R.id.txtProductDescription);
            TextView productPrice = view.findViewById(R.id.txtProductPrice);

            Glide.with(view)
                    .load(productModelList.get(position).getProductImage())
                    .into(productImage);
            productName.setText(productModelList.get(position).getProductName());
            productDescription.setText(productModelList.get(position).getProductDescription());
            productPrice.setText(productModelList.get(position).getProductPrice());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent productDetailsIntent = new Intent(view.getContext(), ProductDetailsActivity.class);
                    view.getContext().startActivity(productDetailsIntent);

                }
            });
        }else{
            view = convertView;
        }
        return view;
    }
}
