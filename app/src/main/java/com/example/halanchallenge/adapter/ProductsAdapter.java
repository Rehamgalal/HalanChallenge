package com.example.halanchallenge.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.halanchallenge.databinding.ProductItemBinding;
import com.example.halanchallenge.model.Product;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    private final List<com.example.halanchallenge.model.Product> mData;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public ProductsAdapter(List<com.example.halanchallenge.model.Product> data) {
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProductItemBinding view = ProductItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product item = mData.get(position);
        holder.bind(item);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ProductItemBinding binding;
        ViewHolder(ProductItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void bind(Product item) {
            if (item != null){
                binding.setItem(item);
                binding.setListener(mClickListener);
            }
        }
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(Product product);
    }
}
