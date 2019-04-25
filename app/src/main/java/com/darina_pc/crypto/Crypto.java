package com.darina_pc.crypto;

import android.support.annotation.NonNull;

public class Crypto implements Asset{

    private String assetCode;
    private String asset_type;
    private String category;
    private String representationName;
    private AssetIssuer asset_issuer;
    private int rating;
    private Boolean is_counter;

    public Crypto(String assetCode, String asset_type, String category, String representationName, AssetIssuer asset_issuer, int rating, Boolean is_counter) {
        this.assetCode = assetCode;
        this.asset_type = asset_type;
        this.category = category;
        this.representationName = representationName;
        this.asset_issuer = asset_issuer;
        this.rating = rating;
        this.is_counter = is_counter;
    }

    @Override
    public String getAssetCode() {
        return assetCode;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public String getRepresentationName() {
        return representationName;
    }

    @Override
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

    public AssetIssuer getAsset_issuer() {
        return asset_issuer;
    }

    public void setAsset_issuer(AssetIssuer asset_issuer) {
        this.asset_issuer = asset_issuer;
    }

    @Override
    public boolean getIsCounter() {
        return is_counter;
    }

    public void setIsCounter(Boolean is_counter) {
        this.is_counter = is_counter;
    }

    @Override
    public int getAssetType() {
        return Asset.CRYPTO;
    }

    @Override
    public int compareTo(@NonNull Asset otherAsset) {
        if (otherAsset.getRating() == this.getRating()) return 0;
        else if (otherAsset.getRating()> this.getRating()) return -1;
        else return 1;
    }
}
