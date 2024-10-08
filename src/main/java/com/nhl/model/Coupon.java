package com.nhl.model;

public class Coupon {

    private int salePercentage;

    public Coupon(int salePercentage) {
        this.salePercentage = salePercentage;
    }

    public int getSalePercentage() {
        return salePercentage;
    }

    public void setSalePercentage(int salePercentage) {
        this.salePercentage = salePercentage;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "salePercentage=" + salePercentage +
                '}';
    }
}
