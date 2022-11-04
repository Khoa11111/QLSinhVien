package com.google.qlsinhvien.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.qlsinhvien.QLSinhVien;
import com.google.qlsinhvien.R;
import com.google.qlsinhvien.SinhVien.SinhVien;

import java.util.List;

public class SinhVienAdapter extends RecyclerView.Adapter<SinhVienAdapter.SinhVienViewHolder>{

    private QLSinhVien context_217;
    private List<SinhVien> list_217;

    public SinhVienAdapter(QLSinhVien context) {
        this.context_217 = context;
    }

    public void setData(List<SinhVien> list){
        this.list_217 = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SinhVienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sinhvien_row,parent,false);
        return new SinhVienViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SinhVienViewHolder holder, int position) {
        SinhVien sinhVien = list_217.get(position);
        if(sinhVien == null){
            return;
        }

        holder.imgSinhVien.setImageResource(sinhVien.getImgSource());
        holder.tvMaSV.setText(sinhVien.getMaSV());
        holder.tvTen.setText(sinhVien.getTenSV());
        holder.tvEmail.setText(sinhVien.getEmail());

        // bat su kien xoa & sua
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context_217.DialogSua(sinhVien.getMaSV(),sinhVien.getTenSV(),sinhVien.getEmail());
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context_217.DialogXoa(sinhVien.getMaSV());
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list_217 != null) {
            return list_217.size();
        }
        return 0;
    }

    public class SinhVienViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgSinhVien;
        private TextView tvMaSV, tvTen, tvEmail;
        private ImageView imgDelete, imgEdit;

        public SinhVienViewHolder(@NonNull View itemView) {
            super(itemView);

            imgSinhVien = itemView.findViewById(R.id.img_sv);
            tvMaSV = itemView.findViewById(R.id.txt_masv);
            tvTen = itemView.findViewById(R.id.txt_tensv);
            tvEmail = itemView.findViewById(R.id.txt_emailsv);
            imgDelete = itemView.findViewById(R.id.img_delete);
            imgEdit = itemView.findViewById(R.id.img_edit);
        }
    }
}
