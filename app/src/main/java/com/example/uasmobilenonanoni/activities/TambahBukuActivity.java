package com.example.uasmobilenonanoni.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uasmobilenonanoni.R;
import com.example.uasmobilenonanoni.database.BukuDAO;
import com.example.uasmobilenonanoni.models.Buku;

public class TambahBukuActivity extends AppCompatActivity {
    private EditText etJudul, etPenulis, etPenerbit, etTahunTerbit, etJumlahHalaman, etKategori;
    private Button btnSimpan;
    private BukuDAO bukuDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_buku);

        etJudul = findViewById(R.id.et_judul);
        etPenulis = findViewById(R.id.et_penulis);
        etPenerbit = findViewById(R.id.et_penerbit);
        etTahunTerbit = findViewById(R.id.et_tahun_terbit);
        etJumlahHalaman = findViewById(R.id.et_jumlah_halaman);
        etKategori = findViewById(R.id.et_kategori);
        btnSimpan = findViewById(R.id.btn_simpan);

        bukuDAO = new BukuDAO(this);
        bukuDAO.open();

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpanBuku();
            }
        });
    }

    private void simpanBuku() {
        String judul = etJudul.getText().toString().trim();
        String penulis = etPenulis.getText().toString().trim();
        String penerbit = etPenerbit.getText().toString().trim();
        String tahunTerbitStr = etTahunTerbit.getText().toString().trim();
        String jumlahHalamanStr = etJumlahHalaman.getText().toString().trim();
        String kategori = etKategori.getText().toString().trim();

        if (judul.isEmpty() || penulis.isEmpty() || penerbit.isEmpty() || tahunTerbitStr.isEmpty() || jumlahHalamanStr.isEmpty() || kategori.isEmpty()) {
            Toast.makeText(this, "Semua field harus diisi", Toast.LENGTH_SHORT).show();
            return;
        }

        int tahunTerbit = Integer.parseInt(tahunTerbitStr);
        int jumlahHalaman = Integer.parseInt(jumlahHalamanStr);

        Buku buku = new Buku(0, judul, penulis, penerbit, tahunTerbit, jumlahHalaman, kategori);
        long result = bukuDAO.tambahBuku(buku);

        if (result != -1) {
            Toast.makeText(this, "Buku berhasil ditambahkan", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Gagal menambahkan buku", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        bukuDAO.close();
        super.onDestroy();
    }
}
