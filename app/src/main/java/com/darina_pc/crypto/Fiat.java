package com.darina_pc.crypto;

import android.support.annotation.NonNull;

public class Fiat implements Asset {

    private String assetCode;
    private String category;
    private String representationName;
    private int rating;
    private Boolean is_counter;

    public Fiat(String assetCode, String category, String representationName, int rating, Boolean is_counter) {
        this.assetCode = assetCode;
        this.category = category;
        this.representationName = representationName;
        this.rating = rating;
        this.is_counter = is_counter;
    }

    @Override
    public String getAssetCode() {
        return assetCode;
    }

    @Override
    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String getRepresentationName() {
        return representationName;
    }

    @Override
    public void setRepresentationName(String representationName) {
        this.representationName = representationName;
    }

    @Override
    public int getRating() {
        return rating;
    }

    @Override
    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String getAsset_type() {
        return null;
    }

    public Boolean getIs_counter() {
        return is_counter;
    }

    public void setIs_counter(Boolean is_counter) {
        this.is_counter = is_counter;
    }

    public AssetIssuer getAsset_issuer() {
        return null;
    }


}
