package com.darina_pc.crypto;

import android.support.annotation.NonNull;

public interface Asset extends Comparable<Asset>{
    int NATIVE = 0;
    int FIAT = 1;
    int CRYPTO = 2;

    String getAssetCode();
    String getCategory();
    String getRepresentationName();
    int getRating();
    boolean getIsCounter();
    int getAssetType();


    int compareTo(@NonNull Asset asset);
}
