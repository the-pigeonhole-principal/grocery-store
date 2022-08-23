package dfe.offer;

import dfe.product.Product;

public class PriceReductionOffer implements Offer {
    private final String id;
    private int minimumQuantity;
    private double discountedPrice;

    public PriceReductionOffer(String id, int minimumQuantity, double discountedPrice) {
        this.id = id;
        this.minimumQuantity = minimumQuantity;
        this.discountedPrice = discountedPrice;
    }

    @Override
    public String getID() {
        return this.id;
    }

    @Override
    public double calculatePrice(Product product, int quantity) {
        if (quantity < minimumQuantity) {
            return product.getPrice() * quantity;
        } else {
            return discountedPrice * quantity;
        }
    }
}
