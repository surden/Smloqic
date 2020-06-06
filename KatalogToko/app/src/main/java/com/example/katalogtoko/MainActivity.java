package com.example.katalogtoko;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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

public class MainActivity extends AppCompatActivity {
    private MenuAdapter produkAdapter;
    private RecyclerView recyclerView;
    private ArrayList<Produk> produks;
    int jumpdata;
    private RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                        jumpdata = response.length();
                        try {
                            for (int i = 0; i < jumpdata; i++) {
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
}
