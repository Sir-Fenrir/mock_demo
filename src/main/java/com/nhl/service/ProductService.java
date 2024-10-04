package com.nhl.service;

import com.nhl.model.Bill;
import com.nhl.model.Coupon;
import com.nhl.model.Product;
import com.nhl.repository.CouponRepository;
import com.nhl.repository.ProductRepository;

import java.util.List;

public class ProductService {

    private final ProductRepository productRepository;

    private final CouponRepository couponRepository;

    public ProductService(ProductRepository productRepository, CouponRepository couponRepository) {
        this.productRepository = productRepository;
        this.couponRepository = couponRepository;
    }

    public Bill getBill(List<Integer> productIds, List<Integer> couponIds) {
        List<Product> products = productRepository.findByIds(productIds);
        List<Coupon> coupons = couponRepository.getByIds(couponIds);

        double price = products.stream()
                .map(Product::getPrice)
                .reduce(Double::sum)
                .orElse(0.0);

        double discount = coupons.stream()
                .map(Coupon::getSalePercentage)
                .map(percentage -> (100.0 - percentage) / 100)
                .reduce(Double::sum)
                .orElse(1.0);

        return new Bill(products, coupons, price * discount);
    }
}
