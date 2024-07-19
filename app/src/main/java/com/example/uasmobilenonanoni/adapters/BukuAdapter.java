package com.example.uasmobilenonanoni.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uasmobilenonanoni.R;
import com.example.uasmobilenonanoni.models.Buku;

import java.util.List;

public class BukuAdapter extends RecyclerView.Adapter<BukuAdapter.BukuViewHolder> {
    private List<Buku> bukuList;
    private Context context;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Buku buku);
    }

    public BukuAdapter(Context context, List<Buku> bukuList, OnItemClickListener listener) {
        this.context = context;
        this.bukuList = bukuList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BukuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_buku, parent, false);
        return new BukuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BukuViewHolder holder, int position) {
        Buku buku = bukuList.get(position);
        holder.bind(buku, listener);
    }

    @Override
    public int getItemCount() {
        return bukuList.size();
    }

    public class BukuViewHolder extends RecyclerView.ViewHolder {
        TextView tvJudul, tvPenulis;

        public BukuViewHolder(@NonNull View itemView) {
            super(itemView);
            tvJudul = itemView.findViewById(R.id.tv_judul);
            tvPenulis = itemView.findViewById(R.id.tv_penulis);
        }

        public void bind(final Buku buku, final OnItemClickListener listener) {
            tvJudul.setText(buku.getJudul());
            tvPenulis.setText(buku.getPenulis());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(buku);
                }
            });
        }
    }
}