package dfe.checkout;

import dfe.offer.Offer;
import dfe.offer.repository.OfferRepository;
import dfe.product.Product;
import dfe.product.repository.ProductRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Checkout {

    private final ProductRepository productRepository;
    private OfferRepository offerRepository;
    private Map<String, Integer> basket = new HashMap<>();
    private double naiveTotal = 0.0;

    public Checkout(OfferRepository offerRepository, ProductRepository productRepository) {
        this.offerRepository = offerRepository;
        this.productRepository = productRepository;
    }

    public void scan(String id) {
        Optional<Product> productSearch = productRepository.getProductByID(id);
        if (productSearch.isPresent()) {
            addProductToBasket(productSearch.get());
        } else {
            throw new IllegalArgumentException(String.format("Product %s does not exist", id));
        }
    }

    public void unscan(String id) {
        Optional<Product> productSearch = productRepository.getProductByID(id);
        if (productSearch.isPresent()) {
            removeProductFromBasket(productSearch.get());
        } else {
            throw new IllegalArgumentException(String.format("Product %s does not exist", id));
        }
    }

    public double total() {
        double sum = 0.0;
        for (Map.Entry<String, Integer> basketItem : basket.entrySet()) {
            Product product = productRepository.getProductByID(basketItem.getKey()).get();
            Integer quantity = basketItem.getValue();

            Offer offer = offerRepository.getOfferByItemID(product.getId());
            if (offer == null) {
                sum += product.getPrice() * quantity;
            } else {
                sum += offer.calculatePrice(product, quantity);
            }
        }
        return sum;
    }

    public double totalWithoutOffers() {
        return naiveTotal;
    }

    private void addProductToBasket(Product product) {
        basket.put(product.getId(), basket.getOrDefault(product.getId(), 0) + 1);
        naiveTotal += product.getPrice();
    }

    private void removeProductFromBasket(Product product) {
        String productID = product.getId();
        int quantity = basket.getOrDefault(productID, 0);
        if (quantity > 0) {
            if (quantity == 1) {
                basket.remove(productID);
            } else {
                basket.put(productID, quantity - 1);
            }
            naiveTotal -= product.getPrice();
        } else {
            throw new UnsupportedOperationException(String.format("Product %s is not in basket", productID));
        }
    }
}
