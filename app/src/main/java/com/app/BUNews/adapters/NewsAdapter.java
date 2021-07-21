package com.app.BUNews.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.BUNews.R;
import com.app.BUNews.model.NewsArticle;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    Context context;
    ArrayList<NewsArticle> articles;
    private OnItemClickListener onItemClickListener;

    public NewsAdapter(Context context, ArrayList<NewsArticle> articles) {
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_item, parent, false);
        return new  NewsViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.NewsViewHolder holder, int position) {
        holder.tvName.setText(articles.get(position).getTitle().toString());
        holder.timePublished.setText(articles.get(position).getPublishedAt().toString());
        holder.publisherName.setText(articles.get(position).getSource().getName().toString());
        Picasso.get().load(articles.get(position).getUrlToImage()).into(holder.ivNews);


    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvName;
        TextView timePublished;
        TextView publisherName;
        ImageView ivNews;

        OnItemClickListener onItemClickListener;

        public NewsViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);

            itemView.setOnClickListener(this);
            tvName = itemView.findViewById(R.id.tvName);
            timePublished = itemView.findViewById(R.id.publishTime);
            publisherName = itemView.findViewById(R.id.PublisherName);
            ivNews = itemView.findViewById(R.id.ivNews);

            this.onItemClickListener = onItemClickListener;

        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(view, getAdapterPosition());
        }
    }
}
