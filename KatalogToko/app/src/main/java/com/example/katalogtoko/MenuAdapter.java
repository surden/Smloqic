package com.example.katalogtoko;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.sql.Struct;
import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    private Context context;
    private ArrayList<Produk> produks;

    public MenuAdapter(Context mcontext, ArrayList<Produk> produkBarang){
        context = mcontext;
        produks = produkBarang;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_menu,parent,false);

        return new MenuViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        Produk produkBaru = produks.get(position);
        String gambarBaru =  produkBaru.getGambar();
        String nama = produkBaru.getNama();
        String deskripsi = produkBaru.getDeskripsi();
        String harga = produkBaru.getHarga();

        holder.tvNamaData.setText(nama);
        holder.tvDeskripsiData.setText(deskripsi);
        holder.tvHargaData.setText(harga);
        Glide
            .with(context)
            .load(gambarBaru)
            .centerCrop()
            .into(holder.imgData);
    }

    @Override
    public int getItemCount() {
        return produks.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder{
        public ImageView imgData;
        public TextView tvNamaData;
        public TextView tvDeskripsiData;
        public TextView tvHargaData;

        public MenuViewHolder(@NonNull View itemView){
            super(itemView);
            imgData = itemView.findViewById(R.id.img_barang);
            tvNamaData = itemView.findViewById(R.id.tv_barang);
            tvDeskripsiData = itemView.findViewById(R.id.tv_deskripsi);
            tvHargaData = itemView.findViewById(R.id.tv_harga);


         }
    }
}
