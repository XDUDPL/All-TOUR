package com.lpwanw.tour.Entity;

import javax.persistence.*;

@Table(name = "tour_nguoidi")
@Entity
public class TourNguoidiEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nguoidi_id", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "doan_id", nullable = false)
    private TourDoanEntity doan;

    @Lob
    @Column(name = "nguoidi_dsnhanvien")
    private String nguoidiDsnhanvien;

    @Lob
    @Column(name = "nguoidi_dskhach")
    private String nguoidiDskhach;

    public String getNguoidiDskhach() {
        return nguoidiDskhach;
    }

    public void setNguoidiDskhach(String nguoidiDskhach) {
        this.nguoidiDskhach = nguoidiDskhach;
    }

    public String getNguoidiDsnhanvien() {
        return nguoidiDsnhanvien;
    }

    public void setNguoidiDsnhanvien(String nguoidiDsnhanvien) {
        this.nguoidiDsnhanvien = nguoidiDsnhanvien;
    }

    public TourDoanEntity getDoan() {
        return doan;
    }

    public void setDoan(TourDoanEntity doan) {
        this.doan = doan;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}