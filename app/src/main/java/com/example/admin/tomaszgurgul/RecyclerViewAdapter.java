package com.example.admin.tomaszgurgul;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.myViewHolder> {
    private Context context;
    private List<Array> arrays;

    public RecyclerViewAdapter(Context context, List<Array> arrays) {
        this.context = context;
        this.arrays = arrays;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.layout_itemlist, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        holder.titleTextView.setText(arrays.get(position).getTitle());
        holder.descTextView.setText(arrays.get(position).getDesc());
        Picasso.with(holder.itemView.getContext()).load(arrays.get(position).getUrl()).into(holder.imageUrlImageView);
    }

    @Override
    public int getItemCount() {
        return arrays.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView descTextView;
        ImageView imageUrlImageView;

        public myViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descTextView = itemView.findViewById(R.id.descTextView);
            imageUrlImageView = itemView.findViewById(R.id.imageUrlImageView);
        }
    }


}



