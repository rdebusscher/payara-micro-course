package be.rubus.courses.payara.micro.lab.advanced.shop.service;

import be.rubus.courses.payara.micro.lab.advanced.shop.ErrorMessage;
import be.rubus.courses.payara.micro.lab.advanced.shop.model.*;
import be.rubus.courses.payara.micro.lab.advanced.shop.storage.Database;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserItemService {

    @Inject
    private ProductService productService;

    @Inject
    private Database database;

    //We use Instance hee as there are multiple.
    @Inject
    private Instance<PaymentService> paymentServices;

    public List<Product> productsOfUser(User user) {
        return getProducts(user);

    }

    private List<Product> getProducts(User user) {
        UserItems userItems = database.getUserItems().stream()
                .filter(ui -> ui.getUser().equals(user))
                .findAny()
                .orElse(new UserItems());

        // Make sure we can return an empty list when UserItem not initialised yet.
        List<Product> result;
        if (userItems.getProducts() == null) {
            result = Collections.emptyList();
        } else {
            result = new ArrayList<>(userItems.getProducts());
        }
        return result;
    }

    public ErrorMessage addProducts(User user, List<String> productIds, PaymentType paymentType) {
        List<Product> products = getRealProducts(productIds);

        if (products.size() < productIds.size()) {
            return new ErrorMessage("AD02", "Unknown productId encountered");
        }
        List<Product> productsUser = getProducts(user);

        Set<Product> uniqueProducts = new HashSet<>(products);
        uniqueProducts.addAll(productsUser);

        if (!checkAmountPerCategory(uniqueProducts)) {
            return new ErrorMessage("AD03", "Number of items per category exceeded");
        }

        // Remove the already assigned products@
        productsUser.forEach(uniqueProducts::remove);
        if (!uniqueProducts.isEmpty()) {

            database.addProducts(user, uniqueProducts);
            Optional<PaymentService> paymentService = findPaymentService(paymentType);
            paymentService.orElseThrow(() -> new RuntimeException("Payment Service not supported")).performPayment(user, products);
        }
        return null;
    }

    private Optional<PaymentService> findPaymentService(PaymentType paymentType) {
        //
        return paymentServices.stream().filter(ps -> ps.paymentType() == paymentType)
                .findAny();
    }

    private List<Product> getRealProducts(List<String> productIds) {
        return productIds.stream()
                .map(id -> productService.findProductById(id))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private boolean checkAmountPerCategory(Set<Product> uniqueProducts) {
        Map<ProductCategory, List<Product>> categoryListMap = uniqueProducts.stream().collect(Collectors.groupingBy(Product::getProductCategory));

        boolean allowed = true;
        for (Map.Entry<ProductCategory, List<Product>> entry : categoryListMap.entrySet()) {
            if (entry.getKey() == ProductCategory.BIKE) {
                if (entry.getValue().size() > 2) {
                    allowed = false;
                }
            } else {
                if (entry.getValue().size() > 1) {
                    allowed = false;
                }

            }
        }
        return allowed;
    }

    public ErrorMessage removeProducts(User user, List<String> productIds) {
        List<Product> products = getRealProducts(productIds);

        if (products.size() < productIds.size()) {
            return new ErrorMessage("AD02", "Unknown productId encountered");
        }

        List<Product> productsUser = getProducts(user);

        Set<Product> productsToBeRemoved = products
                .stream().filter(productsUser::contains)
                .collect(Collectors.toSet());

        database.removeProducts(user, productsToBeRemoved);
        return null;

    }
}
