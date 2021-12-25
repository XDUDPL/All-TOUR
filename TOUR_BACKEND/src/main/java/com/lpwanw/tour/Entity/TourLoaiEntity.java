package com.lpwanw.tour.Entity;

import lombok.*;

import javax.persistence.*;

@Table(name = "tour_loai")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class TourLoaiEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loai_id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "loai_ten", nullable = false)
    private String loaiTen;

    @Lob
    @Column(name = "loai_mota", nullable = false)
    private String loaiMota;
}