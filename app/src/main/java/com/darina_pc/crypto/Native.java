package com.darina_pc.crypto;

import android.support.annotation.NonNull;

public class Native implements Asset{

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

    public String getCategory() {
        return category;
    }

    public String getRepresentationName() {
        return representationName;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public boolean getIsCounter() {
        return is_counter;
    }

    @Override
    public int getAssetType() {
        return Asset.NATIVE;
    }

    @Override
    public int compareTo(@NonNull Asset otherAsset) {
        if (otherAsset.getRating() == this.getRating()) return 0;
        else if (otherAsset.getRating()> this.getRating()) return -1;
        else return 1;
    }

}
