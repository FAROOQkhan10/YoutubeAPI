package com.farooqkhan.youtubeapi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.viewholder> {
   Context context;
   ArrayList<YoutubeModel> list;

    public VideoAdapter(Context context, ArrayList<YoutubeModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem,parent,false);
        return new viewholder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
         final  YoutubeModel model = list.get(position);
         holder.textView.setText(model.getTitle());
        Picasso.get()
                .load(model.getUrl())
                .into(holder.imageView);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Video.class);
                intent.putExtra("videoid",model.getVideoId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class viewholder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview);
            textView = itemView.findViewById(R.id.title);
        }
    }
}
