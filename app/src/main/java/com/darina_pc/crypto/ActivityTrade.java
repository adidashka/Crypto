package com.darina_pc.crypto;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
    MyRecyclerViewAdapter2 adapter;

    private TradeResponse.Embedded myRecords;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade);

        base_asset_type = getIntent().getExtras().getString("base_asset_type");
        base_asset_code = getIntent().getExtras().getString("base_asset_code");
        base_asset_issuer = getIntent().getExtras().getString("base_asset_issuer");

        myRecords = null;




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
                        myRecords = response.body()._embedded;
                        RecyclerView recyclerView = findViewById(R.id.recyclerView_trade);
                        int numberOfColumns = 1;
                        recyclerView.setLayoutManager(new GridLayoutManager(ActivityTrade.this, numberOfColumns));
                        adapter = new MyRecyclerViewAdapter2(ActivityTrade.this, myRecords);
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
//        if (myRecords!=null) {
//            RecyclerView recyclerView = findViewById(R.id.recyclerView);
//            int numberOfColumns = 1;
//            recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
//            adapter = new MyRecyclerViewAdapter2(this, myRecords);
//            recyclerView.setAdapter(adapter);
//        }












    }

}

//
//    JSONObject jsonObj = new JSONObject(String.valueOf(response));
//                            jsonObj = jsonObj.getJSONObject("_embedded");
//                                    JSONArray records = jsonObj.getJSONArray("records");
//
//                                    for (int i = 0; i < records.length(); i++) {
//        JSONObject c = records.getJSONObject(i);
//
//        TradeResponse.Record myRecord = new TradeResponse.Record(null,
//        c.getString("id"),
//        c.getString("paging_token"),
//        c.getString("ledger_close_time"),
//        c.getString("offer_id"),
//        c.getString("base_account"),
//        c.getInt("base_amount"),
//        c.getString("base_asset_type"),
//        c.getString("base_asset_code"),
//        c.getString("base_asset_issuer"),
//        c.getString("counter_account"),
//        c.getInt("counter_amount"),
//        c.getString("counter_asset_type"),
//        c.getBoolean("base_is_seller"),
//        new TradeResponse.Price(c.getJSONObject("price").getInt("n"),
//        c.getJSONObject("price").getInt("d")));
//        myRecords.add(myRecord);
//        }