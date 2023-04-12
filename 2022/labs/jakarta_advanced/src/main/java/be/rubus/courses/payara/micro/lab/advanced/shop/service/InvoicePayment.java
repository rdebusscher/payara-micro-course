package be.rubus.courses.payara.micro.lab.advanced.shop.service;

import be.rubus.courses.payara.micro.lab.advanced.shop.model.PaymentType;
import be.rubus.courses.payara.micro.lab.advanced.shop.model.Product;
import be.rubus.courses.payara.micro.lab.advanced.shop.model.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class InvoicePayment extends AbstractPayment {
    @Override
    public void performPayment(User user, List<Product> products) {
        // Instead of sending the data to invoicing system, we just do write out.
        // Just duplicated code from CardPayment as it should normally be two different implementations
    }

    @Override
    public PaymentType paymentType() {
        return PaymentType.INVOICE;
    }
}
