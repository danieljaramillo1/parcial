package com.moviles1.parcial.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moviles1.parcial.Entities.Product;
import com.moviles1.parcial.databinding.ProductItemBinding;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{
    private Context context;
    private ProductItemBinding productItemBinding;
    private ArrayList<Product> productArrayList;

    public ProductAdapter(Context context, ArrayList<Product> productArrayList)
    {
        this.context = context;

    }


    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        productItemBinding = productItemBinding.inflate(LayoutInflater.from(context));

        return new ProductViewHolder(productItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {
        Product product = productArrayList.get(position);
        holder.itemBinding.tvProductName.setText(product.getName());
        holder.itemBinding.tvCategory.setText(product.getCategory());
        holder.itemBinding.tvPrice.setText(product.getPrice());
        holder.itemBinding.tvDescription.setText(product.getDescription());
        holder.itemBinding.tvStock.setText(product.getStock());

    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        ProductItemBinding itemBinding;
        public ProductViewHolder(@NonNull ProductItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = productItemBinding ;

        }
    }
}
