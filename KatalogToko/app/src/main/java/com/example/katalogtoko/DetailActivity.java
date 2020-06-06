package com.example.katalogtoko;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import static com.example.katalogtoko.MainActivity.EXTRA_DESKRIPSI;
import static com.example.katalogtoko.MainActivity.EXTRA_GAMBAR;
import static com.example.katalogtoko.MainActivity.EXTRA_HARGA;
import static com.example.katalogtoko.MainActivity.EXTRA_NAMA;
import static com.example.katalogtoko.MainActivity.EXTRA_SPESIFIKASI;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    private String nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String dataGambar = intent.getStringExtra(EXTRA_GAMBAR);
        String dataNama = intent.getStringExtra(EXTRA_NAMA);
        String dataDeskripsi = intent.getStringExtra(EXTRA_DESKRIPSI);
        String dataSpesifikasi = intent.getStringExtra(EXTRA_SPESIFIKASI);
        String dataHarga = intent.getStringExtra(EXTRA_HARGA);

        dataSpesifikasi= dataSpesifikasi.replace("\\n","\n");

        TextView tvNama = findViewById(R.id.tv_detail_nama);
        TextView tvDeskripsi = findViewById(R.id.tv_detail_deskripsi);
        TextView tvSpesifikasi = findViewById(R.id.tv_detail_spesifikasi);
        TextView tvHarga = findViewById(R.id.tv_detail_harga);
        ImageView imgGambar = findViewById(R.id.img_detail_barang);
        Button btnPesan = findViewById(R.id.btn_pesan);


        tvNama.setText(dataNama);
        tvDeskripsi.setText(dataDeskripsi);
        tvSpesifikasi.setText(dataSpesifikasi);
        tvHarga.setText(dataHarga);
        Glide
                .with(this)
                .load(dataGambar)
                .into(imgGambar);

        nama = dataNama;
        btnPesan.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        try {
            String text = "Om Swastiastu, Pesan Laptop " + nama;

            String toNumber = "6285934241931";

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+toNumber +"&text="+text));
            startActivity(intent);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
