package com.lpwanw.tour.Entity;

import javax.persistence.*;

@Table(name = "tour_loaichiphi")
@Entity
public class TourLoaichiphiEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cp_id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "cp_ten", nullable = false)
    private String cpTen;

    @Lob
    @Column(name = "cp_mota", nullable = false)
    private String cpMota;

    public String getCpMota() {
        return cpMota;
    }

    public void setCpMota(String cpMota) {
        this.cpMota = cpMota;
    }

    public String getCpTen() {
        return cpTen;
    }

    public void setCpTen(String cpTen) {
        this.cpTen = cpTen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}