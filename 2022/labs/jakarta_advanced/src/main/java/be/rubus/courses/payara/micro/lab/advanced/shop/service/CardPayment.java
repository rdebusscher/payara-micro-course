package be.rubus.courses.payara.micro.lab.advanced.shop.service;

import be.rubus.courses.payara.micro.lab.advanced.shop.model.PaymentType;
import be.rubus.courses.payara.micro.lab.advanced.shop.model.Product;
import be.rubus.courses.payara.micro.lab.advanced.shop.model.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class CardPayment extends AbstractPayment {
    @Override
    public void performPayment(User user, List<Product> products) {
        // Instead of sending the data to Credit Card service, we just print it out.
        // Just duplicated code from InvoicePayment as it should normally be two different implementations
    }

    @Override
    public PaymentType paymentType() {
        return PaymentType.CREDIT_CARD;
    }
}
