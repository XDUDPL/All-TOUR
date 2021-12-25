package com.lpwanw.tour.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "tour_gia", indexes = {
        @Index(name = "tour_id", columnList = "tour_id")
})
@Entity
public class TourGiaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gia_id", nullable = false)
    private Integer id;

    @Column(name = "gia_sotien", nullable = false, precision = 10)
    private BigDecimal giaSotien;

    @Column(name = "tour_id", nullable = false)
    private Integer tourId;

    @Column(name = "gia_tungay", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate giaTungay;

    @Lob
    @Column(name = "ghichu", nullable = false)
    private String ghichu;

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public LocalDate getGiaTungay() {
        return giaTungay;
    }

    public void setGiaTungay(LocalDate giaTungay) {
        this.giaTungay = giaTungay;
    }

    public Integer getTourId() {
        return tourId;
    }

    public void setTourId(Integer tourId) {
        this.tourId = tourId;
    }

    public BigDecimal getGiaSotien() {
        return giaSotien;
    }

    public void setGiaSotien(BigDecimal giaSotien) {
        this.giaSotien = giaSotien;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}