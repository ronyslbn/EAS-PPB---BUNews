package com.app.BUNews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.app.BUNews.adapters.NewsAdapter;
import com.app.BUNews.model.NewsArticle;
import com.app.BUNews.viewmodels.NewsViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<NewsArticle> articleArrayList = new ArrayList<>();
    NewsAdapter newsAdapter;
    RecyclerView rvHeadline;
    NewsViewModel newsViewModel;

    String country;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvHeadline = findViewById(R.id.rvNews);



        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        newsViewModel.init("id");
        newsViewModel.getNewsRepository().observe(this, newsResponse -> {
            List<NewsArticle> newsArticles = newsResponse.getArticles();
            articleArrayList.addAll(newsArticles);
            newsAdapter.notifyDataSetChanged();
        });

        setupRecyclerView();
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.idBtn:
                if (checked)
                    country = "id";
                    rvHeadline.removeAllViewsInLayout();
                    newsViewModel.getNewsRepository().observe(this, newsResponse -> {
                    List<NewsArticle> newsArticles = newsResponse.getArticles();
                    articleArrayList.addAll(newsArticles);
                    newsAdapter.notifyDataSetChanged();
                });
                    newsViewModel.init(country);
                    Toast.makeText(MainActivity.this, country, Toast.LENGTH_SHORT).show();
                    break;
            case R.id.enBtn:
                if (checked)
                    country = "en";
                    rvHeadline.removeAllViews();
                    newsViewModel.init(country);
                    newsViewModel.getNewsRepository().observe(this, newsResponse -> {
                        List<NewsArticle> newsArticles = newsResponse.getArticles();
                        articleArrayList.addAll(newsArticles);
                        newsAdapter.notifyDataSetChanged();
                    });
                    Toast.makeText(MainActivity.this, country, Toast.LENGTH_SHORT).show();
                    break;
        }
    }


    private void setupRecyclerView() {
        if (newsAdapter == null) {
            newsAdapter = new NewsAdapter(MainActivity.this, articleArrayList);
            rvHeadline.setLayoutManager(new LinearLayoutManager(this));
            rvHeadline.setAdapter(newsAdapter);
            rvHeadline.setItemAnimator(new DefaultItemAnimator());
            rvHeadline.setNestedScrollingEnabled(true);
        } else {
            newsAdapter.notifyDataSetChanged();
        }
        initListener();
    }

    private void initListener() {

        newsAdapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                NewsArticle article = articleArrayList.get(position);
                Intent detailBerita = new Intent (MainActivity.this, DetailBerita.class);
                detailBerita.putExtra("title", article.getTitle());
                detailBerita.putExtra("urlBerita", article.getUrl());
                detailBerita.putExtra("time", article.getPublishedAt());
                detailBerita.putExtra("source", article.getSource().getName());
                detailBerita.putExtra("author", article.getAuthor());
                detailBerita.putExtra("image_url", article.getUrlToImage());
                detailBerita.putExtra("deskripsi", article.getDescription());
                startActivity(detailBerita);
            }
        });
    }



}
