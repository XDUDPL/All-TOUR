package com.lpwanw.tour.Entity;

import javax.persistence.*;

@Table(name = "tour_diadiem")
@Entity
public class TourDiadiemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dd_id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "dd_thanhpho", nullable = false)
    private String ddThanhpho;

    @Lob
    @Column(name = "dd_ten", nullable = false)
    private String ddTen;

    @Lob
    @Column(name = "dd_mota", nullable = false)
    private String ddMota;

    public String getDdMota() {
        return ddMota;
    }

    public void setDdMota(String ddMota) {
        this.ddMota = ddMota;
    }

    public String getDdTen() {
        return ddTen;
    }

    public void setDdTen(String ddTen) {
        this.ddTen = ddTen;
    }

    public String getDdThanhpho() {
        return ddThanhpho;
    }

    public void setDdThanhpho(String ddThanhpho) {
        this.ddThanhpho = ddThanhpho;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}