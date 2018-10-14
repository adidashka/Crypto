package com.darina_pc.crypto;

public interface Asset {
    int NATIVVE =   0;
    int FIAT= 1;
    int CRYPTO = 2;

    public String assetCode = null;
    public String category = null;
    public String representationName = null;
    public int rating = 0;

    public String getAssetCode();
    public void setAssetCode(String assetCode);
    public String getCategory();
    public void setCategory(String category);
    public String getRepresentationName();
    public void setRepresentationName(String representationName);
    public int getRating();
    public void setRating(int rating) ;

    public AssetIssuer getAsset_issuer();


}