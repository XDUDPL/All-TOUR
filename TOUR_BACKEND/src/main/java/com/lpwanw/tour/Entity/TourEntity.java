package com.lpwanw.tour.Entity;

import javax.persistence.*;
import java.util.Set;

@Table(name = "tours", indexes = {
        @Index(name = "loai_id", columnList = "loai_id")
})
@Entity
public class TourEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tour_id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "tour_ten", nullable = false)
    private String tourTen;

    @Lob
    @Column(name = "tour_mota", nullable = false)
    private String tourMota;

    @ManyToOne(optional = false)
    @JoinColumn(name = "loai_id", nullable = false)
    private TourLoaiEntity loai;

    public TourLoaiEntity getLoai() {
        return loai;
    }

    public void setLoai(TourLoaiEntity loai) {
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
}