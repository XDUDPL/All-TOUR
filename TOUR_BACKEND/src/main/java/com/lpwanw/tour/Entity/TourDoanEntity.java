package com.lpwanw.tour.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "tour_doan", indexes = {
        @Index(name = "tour_id", columnList = "tour_id")
})
@Entity
public class TourDoanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doan_id", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tour_id", nullable = false)
    private TourEntity tour;

    @Lob
    @Column(name = "doan_name", nullable = false)
    private String doanName;

    @Column(name = "doan_ngaydi", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate doanNgaydi;

    @Column(name = "doan_ngayve", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate doanNgayve;

    @ManyToOne(optional = false)
    @JoinColumn(name = "gia_id", nullable = false)
    private TourGiaEntity gia;

    @Lob
    @Column(name = "doan_chitietchuongtrinh", nullable = false)
    private String doanChitietchuongtrinh;

    public String getDoanChitietchuongtrinh() {
        return doanChitietchuongtrinh;
    }

    public void setDoanChitietchuongtrinh(String doanChitietchuongtrinh) {
        this.doanChitietchuongtrinh = doanChitietchuongtrinh;
    }

    public TourGiaEntity getGia() {
        return gia;
    }

    public void setGia(TourGiaEntity gia) {
        this.gia = gia;
    }

    public LocalDate getDoanNgayve() {
        return doanNgayve;
    }

    public void setDoanNgayve(LocalDate doanNgayve) {
        this.doanNgayve = doanNgayve;
    }

    public LocalDate getDoanNgaydi() {
        return doanNgaydi;
    }

    public void setDoanNgaydi(LocalDate doanNgaydi) {
        this.doanNgaydi = doanNgaydi;
    }

    public String getDoanName() {
        return doanName;
    }

    public void setDoanName(String doanName) {
        this.doanName = doanName;
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