package com.darina_pc.crypto;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter2 extends RecyclerView.Adapter{
    public final int RECORD = 3;
    private TradeResponse.Embedded myRecords;
    private LayoutInflater mInflater;

    // data is passed into the constructor
    MyRecyclerViewAdapter2(Context context, TradeResponse.Embedded myRecords) {
        this.mInflater = LayoutInflater.from(context);
        this.myRecords = myRecords;
    }


    @Override
    public int getItemViewType(int position) {

        if (myRecords.records.get(position) instanceof TradeResponse.Record) {
            return RECORD;
        } else  {
            return -1;
        }
    }

    // inflates the cell layout from xml when needed
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == RECORD) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.trades_view, parent, false);
            return new Trade_ViewHolder(view);
        } else  {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        TradeResponse.Record mRecord = myRecords.records.get(position);
        String base_seller = null;


            String ledger_close_time = mRecord.ledger_close_time;
            if (mRecord.base_is_seller)
                 base_seller = "SELL (" + Integer.toString((mRecord.price.n/mRecord.price.d)) + ")";
                else
                 base_seller = "BUY (" + Integer.toString((mRecord.price.n/mRecord.price.d)) + ")";
            String base_amount_asset_code = Float.toString(mRecord.base_amount) + "     " + mRecord.base_asset_code;
            String counter_amount = Float.toString(mRecord.counter_amount) + "  XLM";


            ((Trade_ViewHolder) holder).tV_ledger_close_time.setText(ledger_close_time);
            ((Trade_ViewHolder) holder).tV_base_seller.setText(base_seller);
            ((Trade_ViewHolder) holder).tV_base_amount_asset_code.setText(base_amount_asset_code);
            ((Trade_ViewHolder) holder).tV_counter_amount.setText(counter_amount);


    }

    @Override
    public int getItemCount() {
        return myRecords.records.size();
    }



    public class Trade_ViewHolder extends RecyclerView.ViewHolder  {

        public TextView tV_ledger_close_time, tV_base_seller, tV_base_amount_asset_code, tV_counter_amount;

        public Trade_ViewHolder(View itemView) {
            super(itemView);
            tV_ledger_close_time  = (TextView) itemView.findViewById(R.id.tV_close_time);
            tV_base_seller = (TextView) itemView.findViewById(R.id.tV_base_seller);
            tV_base_amount_asset_code = (TextView) itemView.findViewById(R.id.tV_base_amount_asset_code);
            tV_counter_amount = (TextView) itemView.findViewById(R.id.tV_counter_amount);
        }
    }

}
