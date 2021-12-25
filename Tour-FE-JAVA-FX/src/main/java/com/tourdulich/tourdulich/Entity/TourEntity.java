package com.tourdulich.tourdulich.Entity;

import com.tourdulich.tourdulich.Service.TourService;

import java.util.ArrayList;
import java.util.List;

public class TourEntity {
    private Integer id;
    private String tourTen;
    private String tourMota;
    private LoaiTourEntity loai;


    public TourEntity() {
    }

    public TourEntity(Integer id, String tourTen, String tourMota, LoaiTourEntity loai) {
        this.id = id;
        this.tourTen = tourTen;
        this.tourMota = tourMota;
        this.loai = loai;
    }

    public LoaiTourEntity getLoai() {
        return loai;
    }

    public void setLoai(LoaiTourEntity loai) {
        this.loai = loai;
    }

    public String getTourMota() {
        return tourMota;
    }

    public void setTourMota(String tourMota) {
        this.tourMota = tourMota;
    }

    public String getTourTen() {
        return tourTen;
    }

    public void setTourTen(String tourTen) {
        this.tourTen = tourTen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id +
                "#" + tourTen +
                "#" + tourMota +
                "#" + loai;
    }

    public boolean Compare(TourEntity tour){
        return tour.toString().equals(toString());
    }
}
