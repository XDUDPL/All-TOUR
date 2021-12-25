package com.lpwanw.tour.Entity;

import javax.persistence.*;

@Table(name = "tour_chitiet", indexes = {
        @Index(name = "tour_id", columnList = "tour_id, dd_id"),
        @Index(name = "dd_id", columnList = "dd_id")
})
@Entity
public class TourChitietEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ct_id", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tour_id", nullable = false)
    private TourEntity tour;

    @ManyToOne(optional = false)
    @JoinColumn(name = "dd_id", nullable = false)
    private TourDiadiemEntity dd;

    @Column(name = "ct_thutu", nullable = false)
    private Integer ctThutu;

    public Integer getCtThutu() {
        return ctThutu;
    }

    public void setCtThutu(Integer ctThutu) {
        this.ctThutu = ctThutu;
    }

    public TourDiadiemEntity getDd() {
        return dd;
    }

    public void setDd(TourDiadiemEntity dd) {
        this.dd = dd;
    }

    public TourEntity getTour() {
        return tour;
    }

    public void setTour(TourEntity tour) {
        this.tour = tour;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}