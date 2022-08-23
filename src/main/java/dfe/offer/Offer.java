package dfe.offer;

import dfe.product.Product;

public interface Offer {
    String getID();
    double calculatePrice(Product product, int quantity);
}
