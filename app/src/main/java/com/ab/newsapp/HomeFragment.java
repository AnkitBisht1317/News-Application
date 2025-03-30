package com.ab.newsapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private Adapter adapter;
    private ArrayList<Model> modelArrayList;
    private static final String API_KEY = "4d640fa374464787b00355fabfb0bbf3";  // Replace with valid key
    private static final String COUNTRY = "us";  // Change to "us" to check if India has no results

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.home_recyclerview);
        modelArrayList = new ArrayList<>();
        adapter = new Adapter(getContext(), modelArrayList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        Log.d("HomeFragment", "Fetching news...");
        getNews();

        return view;
    }

    private void getNews() {
        Log.d("HomeFragment", "API Key: " + API_KEY);

        Call<MainNews> call = ApiUtilities.getApiInterface().getNews(COUNTRY, 100, API_KEY);
        call.enqueue(new Callback<MainNews>() {
            @Override
            public void onResponse(Call<MainNews> call, Response<MainNews> response) {
                if (response.isSuccessful() && response.body() != null) {
                    int totalResults = response.body().getTotalResults();
                    Log.d("API_RESPONSE", "Total News Articles: " + totalResults);

                    if (totalResults == 0 || response.body().getArticles().isEmpty()) {
                        Log.e("API_ERROR", "No news articles found!");
                    } else {
                        modelArrayList.addAll(response.body().getArticles());
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    Log.e("API_ERROR", "Failed response: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<MainNews> call, Throwable t) {
                Log.e("API_ERROR", "Network Error: " + t.getMessage());
            }
        });
    }
}
