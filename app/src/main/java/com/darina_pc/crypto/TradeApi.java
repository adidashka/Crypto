package com.darina_pc.crypto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TradeApi {
    @GET("trades")
    Call<TradeResponse> getTrade(
            @Query("base_asset_type") String base_asset_type,
            @Query("base_asset_code") String base_asset_code,
            @Query("base_asset_issuer") String base_asset_issuer,
            @Query("counter_asset_type") String counter_asset_type,
            @Query("limit") int limit,
            @Query("order") String order);
}

