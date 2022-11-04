package com.google.qlsinhvien;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.google.qlsinhvien.Database.Database;

public class SQLQuery extends AppCompatActivity {
    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create database QLSINHVIEN
        database = new Database(this,"QLSINHVIEN",null,1);

        //create table SinhVien
        database.QueryData("CREATE TABLE IF NOT EXISTS SinhVien(" +
                                "maSV char(15) not null PRIMARY KEY," +
                                "tenSV nvarchar(30) not null," +
                                "gioiTinh int not null," +
                                "email varchar(50))");

        //insert into SinhVien
        database.QueryData("INSERT INTO SinhVien " +
                                "VALUES('2050531200410','Nguyễn Phương Nhi',1,'nhiphuong@gmail.com')," +
                                        "('2050531200356','Nguyễn Văn Phát',0,'phatnguyen@gmail.com')," +
                                        "('2050531200201','Đào Thị Nga',1,'ngadao@gmail.com')");

        Cursor data = database.GetData("SELECT * FROM SinhVien");
        while(data.moveToNext()){
            String t = data.getString(1);
            Toast.makeText(this,t,Toast.LENGTH_SHORT).show();
        }
    }
}