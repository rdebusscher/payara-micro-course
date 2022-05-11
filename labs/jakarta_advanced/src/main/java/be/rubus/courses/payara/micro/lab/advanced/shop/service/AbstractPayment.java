package be.rubus.courses.payara.micro.lab.advanced.shop.service;

import be.rubus.courses.payara.micro.lab.advanced.shop.model.Product;

import java.util.List;

public abstract class AbstractPayment implements PaymentService {

    protected double calculateTotalPrice(List<Product> products) {
        return products.stream().map(Product::getPrice).mapToDouble(Double::doubleValue).sum();
    }
}
