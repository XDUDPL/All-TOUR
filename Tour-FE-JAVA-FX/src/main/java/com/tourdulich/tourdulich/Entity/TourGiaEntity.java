package com.tourdulich.tourdulich.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class TourGiaEntity {
    private Integer id;
    private BigDecimal giaSotien;
    private Integer tourId;
    private Date giaTungay;
    private String ghichu;

    public TourGiaEntity(Integer id, BigDecimal giaSotien, Integer tourId, Date giaTungay, String ghichu) {
        this.id = id;
        this.giaSotien = giaSotien;
        this.tourId = tourId;
        this.giaTungay = giaTungay;
        this.ghichu = ghichu;
    }

    public TourGiaEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getGiaSotien() {
        return giaSotien;
    }

    public void setGiaSotien(BigDecimal giaSotien) {
        this.giaSotien = giaSotien;
    }

    public Integer getTourId() {
        return tourId;
    }

    public void setTourId(Integer tourId) {
        this.tourId = tourId;
    }

    public Date getGiaTungay() {
        return giaTungay;
    }

    public void setGiaTungay(Date giaTungay) {
        this.giaTungay = giaTungay;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }
}
