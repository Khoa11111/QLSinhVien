package com.google.qlsinhvien.SinhVien;

public class SinhVien {
    private int imgSource;
    private String maSV, tenSV, email;

    public SinhVien(int imgSource, String maSV, String tenSV, String email) {
        this.imgSource = imgSource;
        this.maSV = maSV;
        this.tenSV = tenSV;
        this.email = email;
    }

    public int getImgSource() {
        return imgSource;
    }

    public void setImgSource(int imgSource) {
        this.imgSource = imgSource;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getTenSV() {
        return tenSV;
    }

    public void setTenSV(String tenSV) {
        this.tenSV = tenSV;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
