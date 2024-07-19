package com.example.uasmobilenonanoni.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uasmobilenonanoni.R;
import com.example.uasmobilenonanoni.database.BukuDAO;
import com.example.uasmobilenonanoni.models.Buku;

public class EditBukuActivity extends AppCompatActivity {
    private EditText etJudul, etPenulis, etPenerbit, etTahunTerbit, etJumlahHalaman, etKategori;
    private Button btnUpdate;
    private BukuDAO bukuDAO;
    private Buku buku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_buku);

        etJudul = findViewById(R.id.et_judul);
        etPenulis = findViewById(R.id.et_penulis);
        etPenerbit = findViewById(R.id.et_penerbit);
        etTahunTerbit = findViewById(R.id.et_tahun_terbit);
        etJumlahHalaman = findViewById(R.id.et_jumlah_halaman);
        etKategori = findViewById(R.id.et_kategori);
        btnUpdate = findViewById(R.id.btn_update);

        bukuDAO = new BukuDAO(this);
        bukuDAO.open();

        buku = getIntent().getParcelableExtra("buku");
        if (buku != null) {
            tampilkanDataBuku();
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBuku();
            }
        });
    }

    private void tampilkanDataBuku() {
        etJudul.setText(buku.getJudul());
        etPenulis.setText(buku.getPenulis());
        etPenerbit.setText(buku.getPenerbit());
        etTahunTerbit.setText(String.valueOf(buku.getTahunTerbit()));
        etJumlahHalaman.setText(String.valueOf(buku.getJumlahHalaman()));
        etKategori.setText(buku.getKategori());
    }

    private void updateBuku() {
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

        buku.setJudul(judul);
        buku.setPenulis(penulis);
        buku.setPenerbit(penerbit);
        buku.setTahunTerbit(tahunTerbit);
        buku.setJumlahHalaman(jumlahHalaman);
        buku.setKategori(kategori);

        int result = bukuDAO.updateBuku(buku);

        if (result > 0) {
            Toast.makeText(this, "Buku berhasil diperbarui", Toast.LENGTH_SHORT).show();

            Intent resultIntent = new Intent();
            resultIntent.putExtra("updated_buku", buku);
            setResult(RESULT_OK, resultIntent);

            finish();
        } else {
            Toast.makeText(this, "Gagal memperbarui buku", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        bukuDAO.close();
        super.onDestroy();
    }
}