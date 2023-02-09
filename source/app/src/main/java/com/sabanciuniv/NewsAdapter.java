package com.sabanciuniv;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sabanciuniv.model.News;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>{
    Context ctx;
    List<News> data;

    public NewsAdapter(Context ctx, List<News> data){
        this.ctx = ctx;
        this.data = data;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(ctx).inflate(R.layout.news_row,parent,false);
        NewsViewHolder viewHolder = new NewsViewHolder(row);
        viewHolder.setIsRecyclable(true);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News current = data.get(position);
        holder.title.setText(current.getTitle());
        holder.date.setText(current.getDate().toString());
        holder.downloadImage(((NewsApplication)((AppCompatActivity)ctx).getApplication()).srv, current.getImgPath());
        holder.row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent i = new Intent(ctx, NewsDetailActivity.class);
                 i.putExtra("newsId", current.getId());
                 ctx.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView title;
        TextView date;
        CardView row;
        boolean imageDownloaded;

        Handler imgHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                Log.d("dev", "Setting up the image in NewsAdapter for: " + title.getText());
                Bitmap imgs = (Bitmap) msg.obj;
                img.setImageBitmap(imgs);
                return true;
            }
        });

        public NewsViewHolder(@NonNull View itemView){
            super(itemView);
            img = itemView.findViewById(R.id.rowImg);
            title = itemView.findViewById(R.id.rowTitle);
            date = itemView.findViewById(R.id.rowDate);
            row = itemView.findViewById(R.id.row);
        }

        public void downloadImage(ExecutorService srv, String path) {
            if (!imageDownloaded) {
                NewsRepository repo = new NewsRepository();
                repo.downloadImage(srv, imgHandler, path);
                imageDownloaded = true;
            }
        }
    }
}
