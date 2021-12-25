package com.tourdulich.tourdulich.Entity;

public class TourChiTietEntity {
    private Integer id;
    private TourEntity tour;
    private DiaDiemEntity dd;
    private Integer ctThutu;

    public TourChiTietEntity(Integer id, TourEntity tour, DiaDiemEntity dd, Integer ctThutu) {
        this.id = id;
        this.tour = tour;
        this.dd = dd;
        this.ctThutu = ctThutu;
    }

    public TourChiTietEntity() {
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

    public DiaDiemEntity getDd() {
        return dd;
    }

    public void setDd(DiaDiemEntity dd) {
        this.dd = dd;
    }

    public Integer getCtThutu() {
        return ctThutu;
    }

    public void setCtThutu(Integer ctThutu) {
        this.ctThutu = ctThutu;
    }
}
