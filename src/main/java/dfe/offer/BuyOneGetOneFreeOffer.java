package dfe.offer;

import dfe.product.Product;

public class BuyOneGetOneFreeOffer implements Offer {

    private String id;

    public BuyOneGetOneFreeOffer(String id) {
        this.id = id;
    }

    @Override
    public String getID() {
        return this.id;
    }

    @Override
    public double calculatePrice(Product product, int quantity) {
        if (quantity < 2) {
            return quantity * product.getPrice();
        } else {
            return product.getPrice() * Math.ceil(quantity * 0.5);
        }
    }
}
