package com.example.katalogtoko;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MenuAdapter.OnItemClickListener {
    public static final String EXTRA_NAMA="datanama";
    public static final String EXTRA_DESKRIPSI="datadeskripsi";
    public static final String EXTRA_SPESIFIKASI="dataspesifikasi";
    public static final String EXTRA_HARGA="dataHarga";
    public static final String EXTRA_GAMBAR="dataGambar";

    private MenuAdapter produkAdapter;
    private RecyclerView recyclerView;
    private ArrayList<Produk> produks;
    int jumpData;
    private RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.rv_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        produks = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);
        parseJSON();


    }

    private void parseJSON(){
        String url = "http://mobile.smloqic.com/json.php";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        jumpData = response.length();
                        try {
                            for (int i = 0; i < jumpData; i++) {
                                JSONObject data = response.getJSONObject(i);
                                String gambarProduk = data.getString("gambar");
                                String namaProduk = data.getString("nama");
                                String deskripsiProduk = data.getString("deskripsi");
                                String spesifikasiProduk = data.getString("spesifikasi");
                                String hargaProduk = data.getString("harga");

                                produks.add(new Produk(namaProduk, hargaProduk, gambarProduk, deskripsiProduk, spesifikasiProduk));
                            }
                            produkAdapter = new MenuAdapter(MainActivity.this, produks);
                            recyclerView.setAdapter(produkAdapter);
                            produkAdapter.setOnItemClickListener(MainActivity.this);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
    });
        requestQueue.add(request);
    }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this,DetailActivity.class);
        Produk clickedItem = produks.get(position);

        detailIntent.putExtra(EXTRA_NAMA, clickedItem.getNama());
        detailIntent.putExtra(EXTRA_DESKRIPSI, clickedItem.getDeskripsi());
        detailIntent.putExtra(EXTRA_SPESIFIKASI, clickedItem.getSpesifikasi());
        detailIntent.putExtra(EXTRA_HARGA, clickedItem.getHarga());
        detailIntent.putExtra(EXTRA_GAMBAR, clickedItem.getGambar());

        startActivity(detailIntent);
    }
}
