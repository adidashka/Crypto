package com.darina_pc.crypto;

import android.support.annotation.NonNull;

public class Native implements Asset {

    private String assetCode;
    private String asset_type;
    private String category;
    private String representationName;
    private int rating;
    private Boolean is_counter;

    public Native(String assetCode, String asset_type, String category, String representationName, int rating,  Boolean is_counter) {
        this.assetCode = assetCode;
        this.category = category;
        this.representationName = representationName;
        this.rating = rating;
        this.asset_type = asset_type;
        this.is_counter = is_counter;
    }

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRepresentationName() {
        return representationName;
    }

    public void setRepresentationName(String representationName) {
        this.representationName = representationName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getAsset_type() {
        return asset_type;
    }

    public void setAsset_type(String asset_type) {
        this.asset_type = asset_type;
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
