package com.example.retrofit_dua;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.retrofit_dua.retrofit.ApiService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private MainAdapter mainAdapter;

    private List<MainModel.Result> results = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getDataFromApi();
        setupView();
        setupRecyclerView();

    }

    private void setupView() {
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
    }

    private void setupRecyclerView(){
        mainAdapter = new MainAdapter(results, new MainAdapter.OnAdapterListener() {
            @Override
            public void onClick(MainModel.Result result) {
                Toast.makeText(MainActivity.this, result.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mainAdapter);
    }

    private void getDataFromApi (){
        ApiService.endpoint().getData()
                .enqueue(new Callback<MainModel>() {
                    @Override
                    public void onResponse(Call<MainModel> call, Response<MainModel> response) {
                        if(response.isSuccessful()) {
                            List<MainModel.Result> results = response.body().getResult();
                            Log.d(TAG, results.toString());
                            mainAdapter.setData(results);
                        }

                    }

                    @Override
                    public void onFailure(Call<MainModel> call, Throwable t) {

                        Log.d(TAG, t.toString());

                    }
                });
    }

}