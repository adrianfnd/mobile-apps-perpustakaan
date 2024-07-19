package com.example.uasmobilenonanoni.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Buku implements Parcelable {
    private int id;
    private String judul;
    private String penulis;
    private String penerbit;
    private int tahunTerbit;
    private int jumlahHalaman;
    private String kategori;

    public Buku() {
    }

    public Buku(int id, String judul, String penulis, String penerbit, int tahunTerbit, int jumlahHalaman, String kategori) {
        this.id = id;
        this.judul = judul;
        this.penulis = penulis;
        this.penerbit = penerbit;
        this.tahunTerbit = tahunTerbit;
        this.jumlahHalaman = jumlahHalaman;
        this.kategori = kategori;
    }

    protected Buku(Parcel in) {
        id = in.readInt();
        judul = in.readString();
        penulis = in.readString();
        penerbit = in.readString();
        tahunTerbit = in.readInt();
        jumlahHalaman = in.readInt();
        kategori = in.readString();
    }

    public static final Creator<Buku> CREATOR = new Creator<Buku>() {
        @Override
        public Buku createFromParcel(Parcel in) {
            return new Buku(in);
        }

        @Override
        public Buku[] newArray(int size) {
            return new Buku[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public int getTahunTerbit() {
        return tahunTerbit;
    }

    public void setTahunTerbit(int tahunTerbit) {
        this.tahunTerbit = tahunTerbit;
    }

    public int getJumlahHalaman() {
        return jumlahHalaman;
    }

    public void setJumlahHalaman(int jumlahHalaman) {
        this.jumlahHalaman = jumlahHalaman;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(judul);
        dest.writeString(penulis);
        dest.writeString(penerbit);
        dest.writeInt(tahunTerbit);
        dest.writeInt(jumlahHalaman);
        dest.writeString(kategori);
    }
}
