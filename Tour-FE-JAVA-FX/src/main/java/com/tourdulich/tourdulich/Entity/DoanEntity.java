package com.tourdulich.tourdulich.Entity;

import java.time.LocalDate;
import java.util.Date;

public class DoanEntity {
    private Integer id;
    private TourEntity tour;
    private String doanName;
    private Date doanNgaydi;
    private Date doanNgayve;
    private TourGiaEntity gia;
    private String doanChitietchuongtrinh;

    public String getDoanChitietchuongtrinh() {
        return doanChitietchuongtrinh;
    }

    public void setDoanChitietchuongtrinh(String doanChitietchuongtrinh) {
        this.doanChitietchuongtrinh = doanChitietchuongtrinh;
    }

    public DoanEntity() {
    }

    public DoanEntity(Integer id, TourEntity tour, String doanName, Date doanNgaydi, Date doanNgayve, TourGiaEntity gia) {
        this.id = id;
        this.tour = tour;
        this.doanName = doanName;
        this.doanNgaydi = doanNgaydi;
        this.doanNgayve = doanNgayve;
        this.gia = gia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TourEntity getTour() {
        return tour;
    }

    public void setTour(TourEntity tour) {
        this.tour = tour;
    }

    public String getDoanName() {
        return doanName;
    }

    public void setDoanName(String doanName) {
        this.doanName = doanName;
    }

    public Date getDoanNgaydi() {
        return doanNgaydi;
    }

    public void setDoanNgaydi(Date doanNgaydi) {
        this.doanNgaydi = doanNgaydi;
    }
    public void setDoanNgaydi(LocalDate doanNgaydi) {
        this.doanNgaydi = java.sql.Date.valueOf(doanNgaydi);
    }

    public Date getDoanNgayve() {
        return doanNgayve;
    }

    public void setDoanNgayve(Date doanNgayve) {
        this.doanNgayve = doanNgayve;
    }
    public void setDoanNgayve(LocalDate doanNgayve) {
        this.doanNgayve = java.sql.Date.valueOf(doanNgayve);
    }

    public TourGiaEntity getGia() {
        return gia;
    }

    public void setGia(TourGiaEntity gia) {
        this.gia = gia;
    }

    @Override
    public String toString() {
        return id +
                "#" + tour +
                "#" + doanName +
                "#" + doanNgaydi +
                "#" + doanNgayve +
                "#" + gia +
                "#" + doanChitietchuongtrinh;
    }
    public boolean Compare(DoanEntity kh){
        return kh.toString().equals(toString());
    }
}
