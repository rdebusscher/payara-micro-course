package be.rubus.courses.payara.micro.lab.advanced.shop.service;

import be.rubus.courses.payara.micro.lab.advanced.shop.model.PaymentType;
import be.rubus.courses.payara.micro.lab.advanced.shop.model.Product;
import be.rubus.courses.payara.micro.lab.advanced.shop.model.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CardPayment extends AbstractPayment {
    @Override
    public void performPayment(User user, List<Product> products) {
        // Instead of sending the data to Credit Card service, we just print it out.
        // Just duplicated code from InvoicePayment as it should normally be two different implementations
        System.out.printf("Products ordered by user %s%n", user.getName());
        String productList = products.stream().map(p -> p.getId() + " - " + p.getName()).collect(Collectors.joining("\n"));
        System.out.println(productList);
        double totalPrice = calculateTotalPrice(products);
        System.out.printf("total Price is %s%n", totalPrice);
        System.out.println("And payed immediately by Card");
    }

    @Override
    public PaymentType paymentType() {
        return PaymentType.CREDIT_CARD;
    }
}
