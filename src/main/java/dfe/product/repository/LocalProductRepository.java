package dfe.product.repository;

import dfe.product.Product;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class LocalProductRepository implements ProductRepository {

    private static Map<String, Product> products;

    public LocalProductRepository(List<Product> productList) {
        products = productList.stream().collect(Collectors.toMap(Product::getId, product -> product));
    }

    public Optional<Product> getProductByID(String id) {
        return Optional.ofNullable(products.getOrDefault(id, null));
    }

}
