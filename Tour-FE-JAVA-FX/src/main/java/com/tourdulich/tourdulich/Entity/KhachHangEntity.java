package com.tourdulich.tourdulich.Entity;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class KhachHangEntity {
    private Integer id;
    private String khTen;
    private String khSdt;
    public Date khNgaysinh;
    private String khEmail;
    private String khCmnd;

    public KhachHangEntity() {
    }

    public KhachHangEntity(Integer id, String khTen, String khSdt, Date khNgaysinh, String khEmail, String khCmnd) {
        this.id = id;
        this.khTen = khTen;
        this.khSdt = khSdt;
        this.khNgaysinh = khNgaysinh;
        this.khEmail = khEmail;
        this.khCmnd = khCmnd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKhTen() {
        return khTen;
    }

    public void setKhTen(String khTen) {
        this.khTen = khTen;
    }

    public String getKhSdt() {
        return khSdt;
    }

    public void setKhSdt(String khSdt) {
        this.khSdt = khSdt;
    }

    public Date getKhNgaysinh() {
        return khNgaysinh;
    }

    public void setKhNgaysinh(Date khNgaysinh) {
        this.khNgaysinh = khNgaysinh;
    }

    public void setKhNgaysinh(LocalDate khNgaysinh) {
        this.khNgaysinh = java.sql.Date.valueOf(khNgaysinh);
    }

    public String getKhEmail() {
        return khEmail;
    }

    public void setKhEmail(String khEmail) {
        this.khEmail = khEmail;
    }

    public String getKhCmnd() {
        return khCmnd;
    }

    public void setKhCmnd(String khCmnd) {
        this.khCmnd = khCmnd;
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String ngaysinh = format.format(khNgaysinh);
        return id +"#" + khTen +
                "#" + khSdt +
                "#" + ngaysinh +
                "#" + khEmail +
                "#" + khCmnd;
    }
    public boolean Compare(KhachHangEntity kh){
        return kh.toString().equals(toString());
    }

}
