package com.example.myfirebaseapphit.adapters;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirebaseapphit.R;
import com.example.myfirebaseapphit.models.State;
import com.example.myfirebaseapphit.recyclerViewInterface;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private ArrayList<State> dataSet;

    private recyclerViewInterface recyclerVI;

    public CustomAdapter(ArrayList<State> dataSet, recyclerViewInterface recyclerVI)

    {
        this.dataSet = dataSet;
        this.recyclerVI = recyclerVI;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView textViewName;
        TextView textViewNativeName;
        ImageView imageViewIcon;

        public MyViewHolder(View itemView)
        {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.card_view);
            textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            textViewNativeName = (TextView) itemView.findViewById(R.id.textViewNativeName);
            imageViewIcon = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder,@SuppressLint("RecyclerView") int position) {
        TextView textViewName = holder.textViewName;
        TextView textViewNativeName = holder.textViewNativeName;
        ImageView imageView = holder.imageViewIcon;
        CardView cardView = holder.cardView;

        textViewName.setText(dataSet.get(position).getName());
        textViewNativeName.setText(dataSet.get(position).getNativeName());
        Glide.with(holder.itemView.getContext()).load(dataSet.get(position).getFlag()).error(R.drawable.ic_launcher_background).into(imageView);
        //Glide.with(holder.itemView.getContext()).load(dataSet.get(position).getFlag()).into(imageView);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerVI.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }


}
