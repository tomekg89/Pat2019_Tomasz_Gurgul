package com.example.admin.tomaszgurgul;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private List<Array.Array_> arrays;

    public RecyclerViewAdapter(Context context, List<Array.Array_> arrays) {
        this.context = context;
        this.arrays = arrays;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.layout_itemlist, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.titleTextView.setText(arrays.get(position).getTitle());
        holder.descTextView.setText(arrays.get(position).getDesc());
        Picasso.with(holder.itemView.getContext()).load(arrays.get(position).getUrl()).into(holder.imageUrlImageView);
    }

    @Override
    public int getItemCount() {
        return arrays.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView descTextView;
        ImageView imageUrlImageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descTextView = itemView.findViewById(R.id.descTextView);
            imageUrlImageView = itemView.findViewById(R.id.imageUrlImageView);
        }
    }
}



