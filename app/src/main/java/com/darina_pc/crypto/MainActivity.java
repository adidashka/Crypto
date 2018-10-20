package com.darina_pc.crypto;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {
    private ArrayList<Asset> myAssets = new ArrayList<Asset>();
    MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            Resources r = this.getResources();
            InputStream stream = r.openRawResource(R.raw.assets_copy);
            String jsonStr = null;
            try {
                jsonStr = convertStreamToString(stream);
            }
            catch(Exception e){
                e.printStackTrace();
            }
            finally {
                stream.close();
            }
            JSONObject jsonObj = new JSONObject(jsonStr);

            // Getting data JSON Array nodes
            JSONArray assets  = jsonObj.getJSONArray("assets");

            // looping through All nodes
            for (int i = 0; i < assets.length(); i++) {
                JSONObject c = assets.getJSONObject(i);

                String _category = c.getString("category");
                if (_category.equals("crypto")){
                    if(c.has("asset_type") && c.getString("asset_type").equals("native")){
                        Native myNative = new Native(c.getString("asset_code"),
                                c.getString("asset_type"),
                                c.getString("category"),
                                c.getString("representation_name"),
                                c.getInt("rating"),
                                c.getBoolean("is_counter"));
                        myAssets.add(myNative);
                    }
                    else {
                        JSONObject jassetIssuer = c.getJSONObject("asset_issuer");
                        AssetIssuer myAssI = new AssetIssuer(jassetIssuer.getString("name"),
                                jassetIssuer.getString("domain"),
                                jassetIssuer.getString("address"));

                        Crypto myCrypto = new Crypto(c.getString("asset_code"),
                                c.getString("asset_type"),
                                c.getString("category"),
                                c.getString("representation_name"),
                                myAssI,
                                c.getInt("rating"),
                                c.getBoolean("is_counter"));
                        myAssets.add(myCrypto);
                    }
                }
                else {
                    Fiat myFiat = new Fiat(c.getString("asset_code"),
                            c.getString("category"),
                            c.getString("representation_name"), c.getInt("rating"),
                            c.getBoolean("is_counter"));
                    myAssets.add(myFiat);
                }
            }
                RecyclerView recyclerView = findViewById(R.id.recyclerView);
                int numberOfColumns = 1;
                recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
                adapter = new MyRecyclerViewAdapter(this, myAssets);
                adapter.setClickListener(this);
                recyclerView.setAdapter(adapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        Asset asset_pressed = myAssets.get(position);
        String base_asset_type = asset_pressed.getAsset_type();
        String base_asset_code = asset_pressed.getAssetCode();
        String base_asset_issuer = asset_pressed.getAsset_issuer().getAddress();

        Intent intent = new Intent(MainActivity.this, ActivityTrade.class);
        intent.putExtra("base_asset_type", base_asset_type);
        intent.putExtra("base_asset_code", base_asset_code);
        intent.putExtra("base_asset_issuer", base_asset_issuer);
        startActivity(intent);
    }

    private String  convertStreamToString(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i = is.read();
        while( i != -1)
        {
            baos.write(i);
            i = is.read();
        }
        return  baos.toString();
    }



}
