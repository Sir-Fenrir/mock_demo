package com.nhl.model;

import java.util.List;

public class Bill {

    private List<Product> products;
    private List<Coupon> coupons;
    private double totalPrice;

    public Bill(List<Product> products, List<Coupon> coupons, double totalPrice) {
        this.products = products;
        this.coupons = coupons;
        this.totalPrice = totalPrice;
    }

    public List<Product> getProducts() {
        return products;
    }


    public List<Coupon> getCoupons() {
        return coupons;
    }


    public double getTotalPrice() {
        return totalPrice;
    }

}
