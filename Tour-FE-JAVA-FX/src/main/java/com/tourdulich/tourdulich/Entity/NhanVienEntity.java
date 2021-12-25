package com.tourdulich.tourdulich.Entity;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class NhanVienEntity {
    private Integer id;
    private String nvTen;
    private String nvSdt;
    private Date nvNgaysinh;
    private String nvEmail;
    private String nvNhiemvu;

    public NhanVienEntity(Integer id, String nvTen, String nvSdt, Date nvNgaysinh, String nvEmail, String nvNhiemvu) {
        this.id = id;
        this.nvTen = nvTen;
        this.nvSdt = nvSdt;
        this.nvNgaysinh = nvNgaysinh;
        this.nvEmail = nvEmail;
        this.nvNhiemvu = nvNhiemvu;
    }

    public NhanVienEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNvTen() {
        return nvTen;
    }

    public void setNvTen(String nvTen) {
        this.nvTen = nvTen;
    }

    public String getNvSdt() {
        return nvSdt;
    }

    public void setNvSdt(String nvSdt) {
        this.nvSdt = nvSdt;
    }

    public Date getNvNgaysinh() {
        return nvNgaysinh;
    }

    public void setNvNgaysinh(Date nvNgaysinh) {
        this.nvNgaysinh = nvNgaysinh;
    }
    public void setNvNgaysinh(LocalDate nvNgaysinh) {
        this.nvNgaysinh = java.sql.Date.valueOf(nvNgaysinh);
    }
    public String getNvEmail() {
        return nvEmail;
    }

    public void setNvEmail(String nvEmail) {
        this.nvEmail = nvEmail;
    }

    public String getNvNhiemvu() {
        return nvNhiemvu;
    }

    public void setNvNhiemvu(String nvNhiemvu) {
        this.nvNhiemvu = nvNhiemvu;
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String ngaysinh = format.format(nvNgaysinh);
        return id +
                "#" + nvTen
                +"#"+ nvSdt
                +"#"+ ngaysinh
                +"#" + nvEmail
                +"#"+ nvNhiemvu;
    }

    public boolean Compare(NhanVienEntity nv){
        return nv.toString().equals(toString());
    }

}
