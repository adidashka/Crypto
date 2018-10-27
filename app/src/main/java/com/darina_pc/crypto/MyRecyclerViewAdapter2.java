package com.darina_pc.crypto;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MyRecyclerViewAdapter2 extends RecyclerView.Adapter{
    public final int RECORD = 3;
    public static final int COLOR_BUY = 0x00FF00;
    public static final int COLOR_SELL = 0xFF0000;
    private List<TradeResponse.Record> myRecords;
    private LayoutInflater mInflater;

    // data is passed into the constructor
    MyRecyclerViewAdapter2(Context context, List<TradeResponse.Record> myRecords) {
        this.mInflater = LayoutInflater.from(context);
        this.myRecords = myRecords;
    }

    @Override
    public int getItemViewType(int position) {

        if (myRecords.get(position) instanceof TradeResponse.Record) {
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
        TradeResponse.Record mRecord = myRecords.get(position);
        String base_seller = null;

        Date mDate = mRecord.get_close_Data();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd/MM/yy' - 'hh:mm");

            String ledger_close_time = formatForDateNow.format(mDate);

            if (mRecord.base_is_seller)
                 base_seller = "SELL (" + Integer.toString((mRecord.price.n/mRecord.price.d)) + ")";
                else
                 base_seller = "BUY (" + Integer.toString((mRecord.price.n/mRecord.price.d)) + ")";
            String base_amount = Float.toString(mRecord.base_amount);
            String counter_amount = Float.toString(mRecord.counter_amount);
            String base_asset_code = mRecord.base_asset_code;
            String xlm = "XLM";

            ((Trade_ViewHolder) holder).tV_ledger_close_time.setText(ledger_close_time);
            ((Trade_ViewHolder) holder).tV_base_seller.setText(base_seller);
            ((Trade_ViewHolder) holder).tV_base_amount.setText(base_amount);
            ((Trade_ViewHolder) holder).tV_counter_amount.setText(counter_amount);
            ((Trade_ViewHolder) holder).tV_base_asset_code.setText(base_asset_code);
            ((Trade_ViewHolder) holder).tV_xlm.setText(xlm);
    }

    @Override
    public int getItemCount() {
        return myRecords.size();
    }

    public class Trade_ViewHolder extends RecyclerView.ViewHolder  {

        public TextView tV_ledger_close_time, tV_base_seller, tV_base_amount, tV_counter_amount, tV_base_asset_code, tV_xlm;

        public Trade_ViewHolder(View itemView) {
            super(itemView);
            tV_ledger_close_time  = (TextView) itemView.findViewById(R.id.tV_close_time);
            tV_base_seller = (TextView) itemView.findViewById(R.id.tV_base_seller);
            tV_base_amount = (TextView) itemView.findViewById(R.id.tV_base_amount);
            tV_counter_amount = (TextView) itemView.findViewById(R.id.tV_counter_amount);
            tV_base_asset_code = (TextView) itemView.findViewById(R.id.tV_base_asset_code);
            tV_xlm = (TextView) itemView.findViewById(R.id.tV_xlm);
        }
    }
}
