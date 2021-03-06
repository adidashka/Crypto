package com.darina_pc.crypto;

import android.support.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TradeResponse {

    public Links _links;
    public Embedded _embedded;


    public class Links {
        public Self self;
        public Next next;
        public Prev prev;
    }

    public class Self{
        String href;
    }
    public class Next {
        String href;
    }
    public class Prev {
        String href;
    }


    public class Embedded {
        ArrayList<Record> records;
    }

    public class Record implements Comparable {

        public LinksR _linksR;
        String id;
        String paging_token;
        String ledger_close_time;
        String offer_id;
        String base_account;
        float base_amount;
        String base_asset_type;
        String base_asset_code;
        String base_asset_issuer;
        String counter_account;
        float counter_amount;
        String counter_asset_type;
        Boolean base_is_seller;
        public Price price;

        public Record(LinksR _linksR, String id, String paging_token, String ledger_close_time, String offer_id, String base_account, float base_amount, String base_asset_type, String base_asset_code, String base_asset_issuer, String counter_account, float counter_amount, String counter_asset_type, Boolean base_is_seller, Price price) {
            this._linksR = _linksR;
            this.id = id;
            this.paging_token = paging_token;
            this.ledger_close_time = ledger_close_time;
            this.offer_id = offer_id;
            this.base_account = base_account;
            this.base_amount = base_amount;
            this.base_asset_type = base_asset_type;
            this.base_asset_code = base_asset_code;
            this.base_asset_issuer = base_asset_issuer;
            this.counter_account = counter_account;
            this.counter_amount = counter_amount;
            this.counter_asset_type = counter_asset_type;
            this.base_is_seller = base_is_seller;
            this.price = price;
        }

        public Date get_close_Data() {
            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd'T'hh:mm:ss'Z'");
            try {
                Date close_Date = ft.parse(ledger_close_time);
                return close_Date;
            }catch (ParseException e) {
                System.out.println(e);
                return null;
            }
        }


        @Override
        public int compareTo(@NonNull Object o) {
            Record otherRecord = (Record) o;
            return this.get_close_Data().compareTo(otherRecord.get_close_Data());
        }
    }

    public class LinksR {
        public Self self;
        public Base base;
        public Counter counter;
        public Operation next;
    }

    public class Base {
        String href;
    }
    public class Counter {
        String href;
    }
    public class Operation {
        String href;
    }

    public static class Price{
        int n;
        int d;

        public Price(int n, int d) {
            this.n = n;
            this.d = d;
        }
    }

}



