package com.darina_pc.crypto;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AssetsRecyclerViewAdapter extends RecyclerView.Adapter{
    private List<Asset> myAssets;
    private OnCryptoClickListener cryptoClickListener;

    interface OnCryptoClickListener {
        void onCryptoClick(Crypto crypto);
    }

    // data is passed into the constructor
    AssetsRecyclerViewAdapter(Context context, List<Asset> myAssets, OnCryptoClickListener cryptoClickListener) {
        this.myAssets = myAssets;
        this.cryptoClickListener = cryptoClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        return myAssets.get(position).getAssetType();
    }

    // inflates the cell layout from xml when needed
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == Asset.NATIVE) {
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
        if (holder.getItemViewType() == Asset.CRYPTO) {
            ((Crypto_ViewHolder) holder).bind((Crypto) mAsset, cryptoClickListener);
        }
        else {
            ((Native_and_Fiat_ViewHolder) holder).bind(mAsset);
        }
    }

    @Override
    public int getItemCount() {
        return myAssets.size();
    }


    public class Native_and_Fiat_ViewHolder extends RecyclerView.ViewHolder  {


        public Native_and_Fiat_ViewHolder(View itemView) {
            super(itemView);
        }

        public void bind(final Asset asset) {
            TextView tV_category  = (TextView) itemView.findViewById(R.id.tV_category);
            TextView tV_rName = (TextView) itemView.findViewById(R.id.tV_rName);
            TextView tV_assetCode_rating = (TextView) itemView.findViewById(R.id.tV_assetCode_rating);
            String categoryName = asset.getCategory();
            String representationName = asset.getRepresentationName();
            String assetCode_and_rating = asset.getAssetCode() + " (" + Integer.toString(asset.getRating()) + ")";

            tV_category.setText(categoryName);
            tV_rName.setText(representationName);
            tV_assetCode_rating.setText(assetCode_and_rating);
        }
    }
    public class Crypto_ViewHolder extends RecyclerView.ViewHolder {

        public Crypto_ViewHolder(View itemView) {
            super(itemView);
        }

        public void bind(final Crypto crypto, final OnCryptoClickListener cryptoClickListener) {
            TextView tV_crypto_category  = (TextView) itemView.findViewById(R.id.tV_crypto_category);
            TextView tV_cpypto_rName = (TextView) itemView.findViewById(R.id.tV_cpypto_rName);
            TextView tV_cpypto_assetCode_rating = (TextView) itemView.findViewById(R.id.tV_cpypto_assetCode_rating);
            TextView tV_cpypto_domain = (TextView) itemView.findViewById(R.id.tV_cpypto_domain);
            View cell = itemView.findViewById(R.id.cryptoViewCell);
            cell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cryptoClickListener.onCryptoClick(crypto);
                }
            });
            String categoryName = crypto.getCategory();
            String representationName = crypto.getRepresentationName();
            String assetCode_and_rating = crypto.getAssetCode() + " (" + Integer.toString(crypto.getRating()) + ")";
            String domain = crypto.getAsset_issuer().getDomain();

            tV_crypto_category.setText(categoryName);
            tV_cpypto_rName.setText(representationName);
            tV_cpypto_assetCode_rating.setText(assetCode_and_rating);
            tV_cpypto_domain.setText(domain);
        }

    }
}
