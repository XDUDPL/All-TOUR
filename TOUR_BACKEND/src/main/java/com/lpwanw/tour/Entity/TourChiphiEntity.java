package com.lpwanw.tour.Entity;

import com.google.gson.Gson;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "tour_chiphi")
@Entity
public class TourChiphiEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chiphi_id", nullable = false)
    private Integer id;

    @OneToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "doan_id", nullable = false)
    private TourDoanEntity doan;

    @Column(name = "chiphi_total", precision = 10)
    private BigDecimal chiphiTotal;

    @Lob
    @Column(name = "chiphi_chitiet")
    private String chiphiChitiet;

    public String getChiphiChitiet() {
        return chiphiChitiet;
    }

    public void setChiphiChitiet(String chiphiChitiet) {
        this.chiphiChitiet = chiphiChitiet;
    }

    public BigDecimal getChiphiTotal() {
        return chiphiTotal;
    }

    public void setChiphiTotal(BigDecimal chiphiTotal) {
        this.chiphiTotal = chiphiTotal;
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
    public BigDecimal CalTotal(){
        BigDecimal result = new BigDecimal(0);
        ChiPhiEntity[] arr = new Gson().fromJson(getChiphiChitiet(),ChiPhiEntity[].class);
        for (ChiPhiEntity a : arr) {
            result = result.add(a.getGia());
        }
        System.out.println(result);
        return result;
    }
}