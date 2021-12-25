package com.lpwanw.tour.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "tour_nhanvien")
@Entity
public class TourNhanvienEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nv_id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "nv_ten", nullable = false)
    private String nvTen;

    @Lob
    @Column(name = "nv_sdt", nullable = false)
    private String nvSdt;

    @Column(name = "nv_ngaysinh", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate nvNgaysinh;

    @Lob
    @Column(name = "nv_email", nullable = false)
    private String nvEmail;

    @Lob
    @Column(name = "nv_nhiemvu", nullable = false)
    private String nvNhiemvu;

    public String getNvNhiemvu() {
        return nvNhiemvu;
    }

    public void setNvNhiemvu(String nvNhiemvu) {
        this.nvNhiemvu = nvNhiemvu;
    }

    public String getNvEmail() {
        return nvEmail;
    }

    public void setNvEmail(String nvEmail) {
        this.nvEmail = nvEmail;
    }

    public LocalDate getNvNgaysinh() {
        return nvNgaysinh;
    }

    public void setNvNgaysinh(LocalDate nvNgaysinh) {
        this.nvNgaysinh = nvNgaysinh;
    }

    public String getNvSdt() {
        return nvSdt;
    }

    public void setNvSdt(String nvSdt) {
        this.nvSdt = nvSdt;
    }

    public String getNvTen() {
        return nvTen;
    }

    public void setNvTen(String nvTen) {
        this.nvTen = nvTen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}