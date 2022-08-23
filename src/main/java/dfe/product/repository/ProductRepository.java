package dfe.product.repository;

import dfe.product.Product;

import java.util.Optional;

public interface ProductRepository {
    Optional<Product> getProductByID(String id);
}
