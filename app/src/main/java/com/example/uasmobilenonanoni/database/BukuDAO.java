package com.example.uasmobilenonanoni.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.uasmobilenonanoni.models.Buku;

import java.util.ArrayList;
import java.util.List;

public class BukuDAO {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public BukuDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long tambahBuku(Buku buku) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_JUDUL, buku.getJudul());
        values.put(DatabaseHelper.COLUMN_PENULIS, buku.getPenulis());
        values.put(DatabaseHelper.COLUMN_PENERBIT, buku.getPenerbit());
        values.put(DatabaseHelper.COLUMN_TAHUN_TERBIT, buku.getTahunTerbit());
        values.put(DatabaseHelper.COLUMN_JUMLAH_HALAMAN, buku.getJumlahHalaman());
        values.put(DatabaseHelper.COLUMN_KATEGORI, buku.getKategori());

        return database.insert(DatabaseHelper.TABLE_BUKU, null, values);
    }

    public int updateBuku(Buku buku) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_JUDUL, buku.getJudul());
        values.put(DatabaseHelper.COLUMN_PENULIS, buku.getPenulis());
        values.put(DatabaseHelper.COLUMN_PENERBIT, buku.getPenerbit());
        values.put(DatabaseHelper.COLUMN_TAHUN_TERBIT, buku.getTahunTerbit());
        values.put(DatabaseHelper.COLUMN_JUMLAH_HALAMAN, buku.getJumlahHalaman());
        values.put(DatabaseHelper.COLUMN_KATEGORI, buku.getKategori());

        return database.update(DatabaseHelper.TABLE_BUKU, values,
                DatabaseHelper.COLUMN_ID + " = ?",
                new String[]{String.valueOf(buku.getId())});
    }

    public void hapusBuku(int id) {
        database.delete(DatabaseHelper.TABLE_BUKU, DatabaseHelper.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public List<Buku> getAllBuku() {
        List<Buku> bukuList = new ArrayList<>();
        Cursor cursor = database.query(DatabaseHelper.TABLE_BUKU,
                null, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Buku buku = cursorToBuku(cursor);
            bukuList.add(buku);
            cursor.moveToNext();
        }
        cursor.close();
        return bukuList;
    }

    public Buku getBukuById(int id) {
        Cursor cursor = database.query(DatabaseHelper.TABLE_BUKU,
                null, DatabaseHelper.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)}, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Buku buku = cursorToBuku(cursor);
        cursor.close();
        return buku;
    }

    private Buku cursorToBuku(Cursor cursor) {
        Buku buku = new Buku();

        int idIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_ID);
        if (idIndex >= 0) {
            buku.setId(cursor.getInt(idIndex));
        }

        int judulIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_JUDUL);
        if (judulIndex >= 0) {
            buku.setJudul(cursor.getString(judulIndex));
        }

        int penulisIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_PENULIS);
        if (penulisIndex >= 0) {
            buku.setPenulis(cursor.getString(penulisIndex));
        }

        int penerbitIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_PENERBIT);
        if (penerbitIndex >= 0) {
            buku.setPenerbit(cursor.getString(penerbitIndex));
        }

        int tahunTerbitIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_TAHUN_TERBIT);
        if (tahunTerbitIndex >= 0) {
            buku.setTahunTerbit(cursor.getInt(tahunTerbitIndex));
        }

        int jumlahHalamanIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_JUMLAH_HALAMAN);
        if (jumlahHalamanIndex >= 0) {
            buku.setJumlahHalaman(cursor.getInt(jumlahHalamanIndex));
        }

        int kategoriIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_KATEGORI);
        if (kategoriIndex >= 0) {
            buku.setKategori(cursor.getString(kategoriIndex));
        }

        return buku;
    }

}
