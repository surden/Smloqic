package com.example.katalogtoko;

public class Produk {
    private String nama;
    private String harga;
    private String gambar;
    private String deskripsi;
    private String spesifikasi;

    public Produk(String dataNama, String dataHarga, String dataGambar, String dataDeskripsi, String dataSpesifikasi){
        nama = dataNama;
        harga = "Rp " + dataHarga;
        gambar = dataGambar;
        deskripsi = dataDeskripsi;
        spesifikasi = dataSpesifikasi;
    }

    public String getNama() {
        return nama;
    }

    public String getHarga() {
        return harga;
    }

    public String getGambar() {
        return gambar;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getSpesifikasi() {
        return spesifikasi;
    }
}
