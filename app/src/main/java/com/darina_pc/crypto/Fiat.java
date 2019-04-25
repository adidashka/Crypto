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

    @Override
    public boolean getIsCounter() {
        return is_counter;
    }

    @Override
    public int getAssetType() {
        return Asset.FIAT;
    }

    @Override
    public int compareTo(@NonNull Asset otherAsset) {
        if (otherAsset.getRating() == this.getRating()) return 0;
        else if (otherAsset.getRating()> this.getRating()) return -1;
        else return 1;
    }


}
