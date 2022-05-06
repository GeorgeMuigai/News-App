package com.gdev.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv_categories, rv_news;
    CategoriesAdapter categoriesAdapter;
    List<CategoriesModal> categoriesModals;
    NewsAdapter newsAdapter;
    List<Articles> newsList;

    TextView txt_getting_news;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_getting_news = findViewById(R.id.txt_getting_news);
        progressBar = findViewById(R.id.progress_circular);

        rv_categories = findViewById(R.id.rv_categories);
        rv_news = findViewById(R.id.rv_news);

        newsList = new ArrayList<>();
        categoriesModals = new ArrayList<>();

        // initialize recyclerview
        initCategories();
        initNews();

        // retrofit client
        retrofitClient();
    }

    private void initNews() {
        rv_news.setLayoutManager(new LinearLayoutManager(this));
        rv_news.setHasFixedSize(true);

        newsAdapter = new NewsAdapter(newsList);
        rv_news.setAdapter(newsAdapter);
    }
    private void retrofitClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NewsApi newsApi = retrofit.create(NewsApi.class);
        Call<NewsModal> news = newsApi.getAllNews();

        news.enqueue(new Callback<NewsModal>() {
            @Override
            public void onResponse(Call<NewsModal> call, Response<NewsModal> response) {
                if(!response.isSuccessful())
                {
                    txt_getting_news.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, "Error code : " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                NewsModal newsModal = response.body();
                ArrayList<Articles> articles = newsModal.getArticles();
                for (int i = 0; i < articles.size(); i++)
                {
                    newsList.add(new Articles(articles.get(i).getTitle(), articles.get(i).getDescription(),
                            articles.get(i).getUrl(), articles.get(i).getUrlToImage(), articles.get(i).getContent()));
                }
                newsAdapter.notifyDataSetChanged();
                txt_getting_news.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<NewsModal> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                txt_getting_news.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void initCategories()
    {
        rv_categories.setHasFixedSize(true);
        rv_categories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        String[] categoriesArray = getResources().getStringArray(R.array.categories);
        String[] urls = getResources().getStringArray(R.array.urls);

        for (int i = 0; i < categoriesArray.length; i++) {
            categoriesModals.add(new CategoriesModal(categoriesArray[i], urls[i]));
        }
        categoriesAdapter = new CategoriesAdapter(categoriesModals);
        categoriesAdapter.notifyDataSetChanged();
        rv_categories.setAdapter(categoriesAdapter);

        Toast.makeText(MainActivity.this, categoriesModals.get(1).getCategory(), Toast.LENGTH_LONG).show();
    }
}