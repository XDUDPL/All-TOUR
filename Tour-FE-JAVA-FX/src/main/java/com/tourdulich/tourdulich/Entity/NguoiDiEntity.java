package com.tourdulich.tourdulich.Entity;

public class NguoiDiEntity {
    private Integer id;
    private DoanEntity doan;
    private String nguoidiDsnhanvien;
    private String nguoidiDskhach;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DoanEntity getDoan() {
        return doan;
    }

    public void setDoan(DoanEntity doan) {
        this.doan = doan;
    }

    public String getNguoidiDsnhanvien() {
        return nguoidiDsnhanvien;
    }

    public void setNguoidiDsnhanvien(String nguoidiDsnhanvien) {
        this.nguoidiDsnhanvien = nguoidiDsnhanvien;
    }

    public String getNguoidiDskhach() {
        return nguoidiDskhach;
    }

    public void setNguoidiDskhach(String nguoidiDskhach) {
        this.nguoidiDskhach = nguoidiDskhach;
    }
}
