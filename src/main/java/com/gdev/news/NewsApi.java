package com.gdev.news;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface NewsApi {

    @GET("v2/top-headlines?category=business&language=en&apiKey=2ac66588640146c3a10590ec1e52bafa")
    Call<NewsModal> getAllNews();

    /*@GET
    Call<NewsModal> getNewsByCategory(@Url String url);*/
}
