package com.tourdulich.tourdulich.Entity;

public class LoaiTourEntity {
    private Integer id;
    private String loaiTen;
    private String loaiMota;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoaiTen() {
        return loaiTen;
    }

    public void setLoaiTen(String loaiTen) {
        this.loaiTen = loaiTen;
    }

    public String getLoaiMota() {
        return loaiMota;
    }

    public void setLoaiMota(String loaiMota) {
        this.loaiMota = loaiMota;
    }

    public LoaiTourEntity(Integer id, String loaiTen, String loaiMota) {
        this.id = id;
        this.loaiTen = loaiTen;
        this.loaiMota = loaiMota;
    }

    public LoaiTourEntity() {
    }

    @Override
    public String toString() {
        return  id +
                "#" + loaiTen +
                "#" + loaiMota;
    }
    public boolean Compare(LoaiTourEntity loaiTour){
        return loaiTour.toString().equals(toString());
    }
}
