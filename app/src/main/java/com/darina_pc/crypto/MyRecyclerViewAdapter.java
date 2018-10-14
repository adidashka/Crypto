package com.darina_pc.crypto;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter{
    private List<Asset> myAssets;
    private LayoutInflater mInflater;
    //private ItemClickListener mClickListener;

    // data is passed into the constructor
    MyRecyclerViewAdapter(Context context, List<Asset> myAssets) {
        this.mInflater = LayoutInflater.from(context);
        this.myAssets = myAssets;
    }

    @Override
    public int getItemViewType(int position) {

        if (myAssets.get(position) instanceof Native) {
            return Asset.NATIVVE;
        } else if (myAssets.get(position) instanceof Fiat) {
            return Asset.FIAT;
        } else if (myAssets.get(position) instanceof Crypto) {
            return Asset.CRYPTO;
        } else {
            return -1;
        }
    }

    // inflates the cell layout from xml when needed
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == Asset.NATIVVE) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.native_and_fiat_view, parent, false);
            return new Native_and_Fiat_ViewHolder(view);
        } else if (viewType == Asset.FIAT) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.native_and_fiat_view, parent, false);
            return new Native_and_Fiat_ViewHolder(view);
        } else if (viewType == Asset.CRYPTO) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.crypto_view, parent, false);
            return new Crypto_ViewHolder(view);
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        Asset mAsset = myAssets.get(position);
        if (holder instanceof Native_and_Fiat_ViewHolder) {

            String categoryName = mAsset.getCategory();
            String representationName = mAsset.getRepresentationName();
            String assetCode_and_rating = mAsset.getAssetCode() + " (" + Integer.toString(mAsset.getRating()) + ")";

            ((Native_and_Fiat_ViewHolder) holder).tV_category.setText(categoryName);
            ((Native_and_Fiat_ViewHolder) holder).tV_rName.setText(representationName);
            ((Native_and_Fiat_ViewHolder) holder).tV_assetCode_rating.setText(assetCode_and_rating);

        } else
            if (holder instanceof Crypto_ViewHolder) {

                String categoryName = mAsset.getCategory();
                String representationName = mAsset.getRepresentationName();
                String assetCode_and_rating = mAsset.getAssetCode() + " (" + Integer.toString(mAsset.getRating()) + ")";
                String domain = mAsset.getAsset_issuer().getDomain();

                ((Crypto_ViewHolder) holder).tV_crypto_category.setText(categoryName);
                ((Crypto_ViewHolder) holder).tV_cpypto_rName.setText(representationName);
                ((Crypto_ViewHolder) holder).tV_cpypto_assetCode_rating.setText(assetCode_and_rating);
                ((Crypto_ViewHolder) holder).tV_cpypto_domain.setText(domain);
            }
    }

    @Override
    public int getItemCount() {
        return myAssets.size();
    }




    public static class Native_and_Fiat_ViewHolder extends RecyclerView.ViewHolder {

        public TextView tV_category, tV_rName, tV_assetCode_rating;

        public Native_and_Fiat_ViewHolder(View itemView) {
            super(itemView);
            tV_category  = (TextView) itemView.findViewById(R.id.tV_category);
            tV_rName = (TextView) itemView.findViewById(R.id.tV_rName);
            tV_assetCode_rating = (TextView) itemView.findViewById(R.id.tV_assetCode_rating);
        }
    }
    public static class Crypto_ViewHolder extends RecyclerView.ViewHolder {

        public TextView tV_crypto_category, tV_cpypto_rName, tV_cpypto_assetCode_rating, tV_cpypto_domain;

        public Crypto_ViewHolder(View itemView) {
            super(itemView);
            tV_crypto_category  = (TextView) itemView.findViewById(R.id.tV_crypto_category);
            tV_cpypto_rName = (TextView) itemView.findViewById(R.id.tV_cpypto_rName);
            tV_cpypto_assetCode_rating = (TextView) itemView.findViewById(R.id.tV_cpypto_assetCode_rating);
            tV_cpypto_domain = (TextView) itemView.findViewById(R.id.tV_cpypto_domain);
        }
    }
}
