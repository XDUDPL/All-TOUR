package com.lpwanw.tour.Entity;

import java.math.BigDecimal;

public class ThongKeEntity {
    private Integer doanId;
    private BigDecimal cost;
    private BigDecimal price;
    private BigDecimal coming;

    public Integer getDoanId() {
        return doanId;
    }

    public void setDoanId(Integer doanId) {
        this.doanId = doanId;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getComing() {
        return coming;
    }

    public void setComing(BigDecimal coming) {
        this.coming = coming;
    }
}
