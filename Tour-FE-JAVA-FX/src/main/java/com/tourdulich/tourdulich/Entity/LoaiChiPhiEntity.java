package com.tourdulich.tourdulich.Entity;

import java.math.BigDecimal;

public class LoaiChiPhiEntity {
    private Integer id;
    private String cpTen;
    private String cpMota;

    public LoaiChiPhiEntity(Integer id, String cpTen, String cpMota) {
        this.id = id;
        this.cpTen = cpTen;
        this.cpMota = cpMota;
    }

    public LoaiChiPhiEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCpTen() {
        return cpTen;
    }

    public void setCpTen(String cpTen) {
        this.cpTen = cpTen;
    }

    public String getCpMota() {
        return cpMota;
    }

    public void setCpMota(String cpMota) {
        this.cpMota = cpMota;
    }

    @Override
    public String toString() {
        return id +
                "#" + cpTen+
                "#" + cpMota;
    }
    public boolean Compare(LoaiChiPhiEntity diaDiemEntity){
        return diaDiemEntity.toString().equals(toString());
    }
}
