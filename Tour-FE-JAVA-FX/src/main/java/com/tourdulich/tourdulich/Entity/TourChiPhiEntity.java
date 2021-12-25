package com.tourdulich.tourdulich.Entity;

import java.math.BigDecimal;

public class TourChiPhiEntity {
    private Integer id;
    private DoanEntity doan;
    private BigDecimal chiphiTotal;
    private String chiphiChitiet;

    public TourChiPhiEntity(Integer id, DoanEntity doan, BigDecimal chiphiTotal, String chiphiChitiet) {
        this.id = id;
        this.doan = doan;
        this.chiphiTotal = chiphiTotal;
        this.chiphiChitiet = chiphiChitiet;
    }

    public TourChiPhiEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DoanEntity getDoan() {
        return doan;
    }

    public void setDoan(DoanEntity doan) {
        this.doan = doan;
    }

    public BigDecimal getChiphiTotal() {
        return chiphiTotal;
    }

    public void setChiphiTotal(BigDecimal chiphiTotal) {
        this.chiphiTotal = chiphiTotal;
    }

    public String getChiphiChitiet() {
        return chiphiChitiet;
    }

    public void setChiphiChitiet(String chiphiChitiet) {
        this.chiphiChitiet = chiphiChitiet;
    }
}
