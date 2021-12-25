package com.tourdulich.tourdulich.Entity;

public class DiaDiemEntity {
    private Integer id;
    private String ddThanhpho;
    private String ddTen;
    private String ddMota;

    public DiaDiemEntity() {
    }

    public DiaDiemEntity(Integer id, String ddThanhpho, String ddTen, String ddMota) {
        this.id = id;
        this.ddThanhpho = ddThanhpho;
        this.ddTen = ddTen;
        this.ddMota = ddMota;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDdThanhpho() {
        return ddThanhpho;
    }

    public void setDdThanhpho(String ddThanhpho) {
        this.ddThanhpho = ddThanhpho;
    }

    public String getDdTen() {
        return ddTen;
    }

    public void setDdTen(String ddTen) {
        this.ddTen = ddTen;
    }

    public String getDdMota() {
        return ddMota;
    }

    public void setDdMota(String ddMota) {
        this.ddMota = ddMota;
    }

    @Override
    public String toString() {
        return id +
                "#" + ddThanhpho +
                "#" + ddTen +
                "#" + ddMota;
    }
    public boolean Compare(DiaDiemEntity diaDiemEntity){
        return diaDiemEntity.toString().equals(toString());
    }
}
