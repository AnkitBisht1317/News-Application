package com.ab.newsapp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnterFragment extends Fragment {
    String API_KEY = "4d640fa374464787b00355fabfb0bbf3";
    RecyclerView recyclerView;
    Adapter adapter;
    ArrayList<Model> modelArrayList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_enter, container, false);
        recyclerView = view.findViewById(R.id.enter_recyclerview);
        modelArrayList = new ArrayList<>();
        adapter = new Adapter(getContext(), modelArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        getNews();
        return view;
    }

    private void getNews() {
        ApiUtilities.getApiInterface().getCatrgory("us", "entertainment", 100, API_KEY).enqueue(new Callback<MainNews>() {
            @Override
            public void onResponse(Call<MainNews> call, Response<MainNews> response) {
                if (response.isSuccessful() && response.body() != null) {
                    modelArrayList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MainNews> call, Throwable t) {
                Log.e("API_FAILURE", t.getMessage());
            }
        });
    }
}
