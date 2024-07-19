package com.example.uasmobilenonanoni.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uasmobilenonanoni.R;
import com.example.uasmobilenonanoni.adapters.BukuAdapter;
import com.example.uasmobilenonanoni.database.BukuDAO;
import com.example.uasmobilenonanoni.models.Buku;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements BukuAdapter.OnItemClickListener {
    private RecyclerView recyclerView;
    private BukuAdapter adapter;
    private BukuDAO bukuDAO;
    private List<Buku> bukuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        FloatingActionButton fabTambah = findViewById(R.id.fab_tambah);

        bukuDAO = new BukuDAO(this);
        bukuDAO.open();

        bukuList = bukuDAO.getAllBuku();
        adapter = new BukuAdapter(this, bukuList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        fabTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TambahBukuActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        bukuList.clear();
        bukuList.addAll(bukuDAO.getAllBuku());
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        bukuDAO.close();
        super.onDestroy();
    }

    @Override
    public void onItemClick(Buku buku) {
        Intent intent = new Intent(MainActivity.this, DetailBukuActivity.class);
        intent.putExtra("buku", buku);
        startActivity(intent);
    }
}