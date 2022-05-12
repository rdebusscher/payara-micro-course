package be.rubus.courses.payara.micro.microstream.config;

import java.util.StringJoiner;

public class Product {

    private long id;
    private String name;
    private ProductType productType;

    public Product(long id, String name, ProductType productType) {
        this.id = id;
        this.name = name;
        this.productType = productType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Product.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("productType=" + productType)
                .toString();
    }
}
