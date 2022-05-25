package be.rubus.courses.payara.micro.lab.advanced.shop.service;

import be.rubus.courses.payara.micro.lab.advanced.shop.model.PaymentType;
import be.rubus.courses.payara.micro.lab.advanced.shop.model.Product;
import be.rubus.courses.payara.micro.lab.advanced.shop.model.User;

import java.util.List;

public interface PaymentService {

    void performPayment(User user, List<Product> products);

    PaymentType paymentType();
}
