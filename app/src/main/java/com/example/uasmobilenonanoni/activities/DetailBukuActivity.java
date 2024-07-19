package com.example.uasmobilenonanoni.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uasmobilenonanoni.R;
import com.example.uasmobilenonanoni.database.BukuDAO;
import com.example.uasmobilenonanoni.models.Buku;

public class DetailBukuActivity extends AppCompatActivity {
    private TextView tvJudul, tvPenulis, tvPenerbit, tvTahunTerbit, tvJumlahHalaman, tvKategori;
    private Button btnEdit, btnHapus;
    private BukuDAO bukuDAO;
    private Buku buku;

    private static final int EDIT_BOOK_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_buku);

        tvJudul = findViewById(R.id.tv_judul);
        tvPenulis = findViewById(R.id.tv_penulis);
        tvPenerbit = findViewById(R.id.tv_penerbit);
        tvTahunTerbit = findViewById(R.id.tv_tahun_terbit);
        tvJumlahHalaman = findViewById(R.id.tv_jumlah_halaman);
        tvKategori = findViewById(R.id.tv_kategori);
        btnEdit = findViewById(R.id.btn_edit);
        btnHapus = findViewById(R.id.btn_hapus);

        bukuDAO = new BukuDAO(this);
        bukuDAO.open();

        buku = getIntent().getParcelableExtra("buku");
        if (buku != null) {
            tampilkanDetailBuku();
        }

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailBukuActivity.this, EditBukuActivity.class);
                intent.putExtra("buku", buku);
                startActivityForResult(intent, EDIT_BOOK_REQUEST);
            }
        });

        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hapusBuku();
            }
        });
    }

    private void tampilkanDetailBuku() {
        tvJudul.setText(buku.getJudul());
        tvPenulis.setText(buku.getPenulis());
        tvPenerbit.setText(buku.getPenerbit());
        tvTahunTerbit.setText(String.valueOf(buku.getTahunTerbit()));
        tvJumlahHalaman.setText(String.valueOf(buku.getJumlahHalaman()));
        tvKategori.setText(buku.getKategori());
    }

    private void hapusBuku() {
        bukuDAO.hapusBuku(buku.getId());
        Toast.makeText(this, "Buku berhasil dihapus", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_BOOK_REQUEST && resultCode == RESULT_OK) {
            buku = data.getParcelableExtra("updated_buku");
            tampilkanDetailBuku();
        }
    }

    @Override
    protected void onDestroy() {
        bukuDAO.close();
        super.onDestroy();
    }
}