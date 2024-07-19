package com.example.uasmobilenonanoni.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "perpustakaan.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_BUKU = "tabel_buku";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_JUDUL = "judul";
    public static final String COLUMN_PENULIS = "penulis";
    public static final String COLUMN_PENERBIT = "penerbit";
    public static final String COLUMN_TAHUN_TERBIT = "tahun_terbit";
    public static final String COLUMN_JUMLAH_HALAMAN = "jumlah_halaman";
    public static final String COLUMN_KATEGORI = "kategori";

    private static final String CREATE_TABLE_BUKU = "CREATE TABLE " + TABLE_BUKU + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_JUDUL + " TEXT NOT NULL, "
            + COLUMN_PENULIS + " TEXT NOT NULL, "
            + COLUMN_PENERBIT + " TEXT NOT NULL, "
            + COLUMN_TAHUN_TERBIT + " INTEGER NOT NULL, "
            + COLUMN_JUMLAH_HALAMAN + " INTEGER NOT NULL, "
            + COLUMN_KATEGORI + " TEXT NOT NULL"
            + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_BUKU);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUKU);
        onCreate(db);
    }
}