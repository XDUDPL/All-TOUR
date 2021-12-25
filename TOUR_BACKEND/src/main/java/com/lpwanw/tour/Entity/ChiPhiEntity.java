package com.lpwanw.tour.Entity;

import java.math.BigDecimal;

public class ChiPhiEntity {
    private Integer loaiChiPhiId;
    private BigDecimal gia;
    private String ghichu;

    public Integer getLoaiChiPhiId() {
        return loaiChiPhiId;
    }

    public void setLoaiChiPhiId(Integer loaiChiPhiId) {
        this.loaiChiPhiId = loaiChiPhiId;
    }

    public BigDecimal getGia() {
        return gia;
    }

    public void setGia(BigDecimal gia) {
        this.gia = gia;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }
}
