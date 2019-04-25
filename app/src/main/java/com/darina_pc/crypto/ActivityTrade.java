package com.darina_pc.crypto;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityTrade extends AppCompatActivity {

    public final String COUNTER_ASSET_TYPE = "native";
    public final int LIMIT = 10;
    public final String ORDER = "desc";
    public static final String BASE_URL = "https://horizon.stellar.org/";

    private Retrofit retrofit;



    private String  base_asset_type, base_asset_code, base_asset_issuer;
    TradesRecyclerView adapter;

    private TradeResponse.Embedded em;
    private ArrayList<TradeResponse.Record> myRecords = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade);

        base_asset_type = getIntent().getExtras().getString("base_asset_type");
        base_asset_code = getIntent().getExtras().getString("base_asset_code");
        base_asset_issuer = getIntent().getExtras().getString("base_asset_issuer");

        myRecords = null;
        em = null;





        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TradeApi tradeApi = retrofit.create(TradeApi.class);
        tradeApi.getTrade(base_asset_type, base_asset_code, base_asset_issuer, COUNTER_ASSET_TYPE,
                LIMIT, ORDER).enqueue(new Callback<TradeResponse>() {
            @Override
            public void onResponse(Call<TradeResponse> call, Response<TradeResponse> response) {
                //Данные успешно пришли, но надо проверить response.body() на null
                if (response.isSuccessful()) {
                    if (response!=null) {
                        em = response.body()._embedded;
                        myRecords = em.records;

                        Collections.sort(myRecords);
                        Collections.reverse(myRecords);

                        RecyclerView recyclerView = findViewById(R.id.recyclerView_trade);
                        int numberOfColumns = 1;
                        recyclerView.setLayoutManager(new GridLayoutManager(ActivityTrade.this, numberOfColumns));
                        adapter = new TradesRecyclerView(ActivityTrade.this, myRecords);
                        recyclerView.setAdapter(adapter);
                    }
                } else {
                    Toast.makeText(ActivityTrade.this, response.code(), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<TradeResponse> call, Throwable t) {
                //Произошла ошибка
                Log.d("error", String.valueOf(t));
            }
        });

    }

}

