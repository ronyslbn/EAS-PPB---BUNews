package com.app.BUNews.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.app.BUNews.model.NewsResponse;
import com.app.BUNews.networking.NewsRepository;


public class NewsViewModel extends ViewModel {

    private MutableLiveData<NewsResponse> mutableLiveData;
    private NewsRepository newsRepository;

    public void init(String country){
        if (mutableLiveData != null){
            return;
        }
        newsRepository = NewsRepository.getInstance();
        mutableLiveData = newsRepository.getNews(country, "063f7a3868c748ec9d667e6ae1d1bf29");

    }

    public LiveData<NewsResponse> getNewsRepository() {
        return mutableLiveData;
    }

}
