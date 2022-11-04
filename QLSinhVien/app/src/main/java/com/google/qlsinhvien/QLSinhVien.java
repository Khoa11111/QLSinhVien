package com.google.qlsinhvien;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.qlsinhvien.Adapter.SinhVienAdapter;
import com.google.qlsinhvien.Database.Database;
import com.google.qlsinhvien.SinhVien.SinhVien;

import java.util.ArrayList;
import java.util.List;

public class QLSinhVien extends AppCompatActivity {

    private RecyclerView recyclerView_217;
    private SinhVienAdapter sinhVienAdapter_217;
    private Database database_217;
    private ImageView imgAdd_217;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sinhvien_list);

        imgAdd_217 = (ImageView) findViewById(R.id.img_add);

        database_217 = new Database(this, "QLSINHVIEN", null, 1);

        recyclerView_217 = (RecyclerView) findViewById(R.id.rcv_sinhvien);
        sinhVienAdapter_217 = new SinhVienAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView_217.setLayoutManager(linearLayoutManager);

        sinhVienAdapter_217.setData(getListSinhVien());
        recyclerView_217.setAdapter(sinhVienAdapter_217);

        imgAdd_217.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogThem();
            }
        });
    }

    private List<SinhVien> getListSinhVien() {
        List<SinhVien> list = new ArrayList<>();
        Cursor data = database_217.GetData("SELECT * FROM SinhVien");
        while (data.moveToNext()) {
            String masv = data.getString(0);
            String tensv = data.getString(1);
            int gt = data.getInt(2);
            String email = data.getString(3);
            if (gt == 0) {
                list.add(new SinhVien(R.drawable.boy_icon, masv, tensv, email));
            } else {
                list.add(new SinhVien(R.drawable.girl_icon, masv, tensv, email));
            }
        }
        sinhVienAdapter_217.notifyDataSetChanged();
        return list;
    }

    public void DialogXoa(String maSV) {
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(this);
        dialogXoa.setMessage("Bạn có muốn xóa sinh viên này hay không?");
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                database_217.QueryData("DELETE FROM SinhVien WHERE maSV = '" + maSV + "'");
                Toast.makeText(QLSinhVien.this, "Đã Xóa", Toast.LENGTH_SHORT).show();
                sinhVienAdapter_217.setData(getListSinhVien());
                recyclerView_217.setAdapter(sinhVienAdapter_217);
            }
        });

        dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        dialogXoa.show();
    }

    public void DialogSua(String maSV, String tenSV, String email) {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_sua);

        final EditText edtTenSV = (EditText) dialog.findViewById(R.id.txtName_update);
        final EditText edtGTSV = (EditText) dialog.findViewById(R.id.txtGT_update);
        final EditText edtEmail = (EditText) dialog.findViewById(R.id.txtEmail_update);
        Button btnXacnhan = (Button) dialog.findViewById(R.id.btn_XacNhan_update);
        Button btnHuy = (Button) dialog.findViewById(R.id.btn_Huy_update);

        edtTenSV.setText(tenSV);
        edtEmail.setText(email);

        btnXacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenSVmoi = edtTenSV.getText().toString().trim();
                String GTSVMoi = edtGTSV.getText().toString().trim();
                String emailMoi = edtEmail.getText().toString().trim();
                database_217.QueryData("UPDATE SinhVien SET tenSV = '" + tenSVmoi + "',gioiTinh = '"+GTSVMoi+"', email = '" + emailMoi + "' WHERE maSV = '" + maSV + "'");
                Toast.makeText(QLSinhVien.this, "Đã Sửa", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                sinhVienAdapter_217.setData(getListSinhVien());
                recyclerView_217.setAdapter(sinhVienAdapter_217);
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void DialogThem() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_sinhvien);

        final EditText edtMaSV = (EditText) dialog.findViewById(R.id.txt_masv_add);
        final EditText edtTenSV = (EditText) dialog.findViewById(R.id.txt_tensv_add);
        final EditText edtGioiTinh = (EditText) dialog.findViewById(R.id.txt_gt_add);
        final EditText edtEmail = (EditText) dialog.findViewById(R.id.txt_email_add);
        Button btnThem = (Button) dialog.findViewById(R.id.btn_Them);
        Button btnHuy = (Button) dialog.findViewById(R.id.btn_Huy_add);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maSV = edtMaSV.getText().toString();
                String tenSV = edtTenSV.getText().toString();
                String gioitinh = edtGioiTinh.getText().toString();
                String email = edtEmail.getText().toString();
                database_217.QueryData("INSERT INTO SinhVien" +
                        " VALUES('" + maSV + "','" + tenSV + "'," + gioitinh + ",'"+email+"')");
                Toast.makeText(QLSinhVien.this, "Đã Thêm", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                sinhVienAdapter_217.setData(getListSinhVien());
                recyclerView_217.setAdapter(sinhVienAdapter_217);
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
