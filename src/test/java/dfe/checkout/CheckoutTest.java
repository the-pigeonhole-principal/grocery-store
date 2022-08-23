package dfe.checkout;

import dfe.offer.BuyOneGetOneFreeOffer;
import dfe.offer.Offer;
import dfe.offer.repository.LocalOfferRepository;
import dfe.offer.PriceReductionOffer;
import dfe.offer.repository.OfferRepository;
import dfe.product.Product;
import dfe.product.repository.LocalProductRepository;
import dfe.product.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckoutTest {
    List<Offer> offers = List.of(
            new BuyOneGetOneFreeOffer("FR1"),
            new PriceReductionOffer("SR1", 3, 4.50)
    );
    OfferRepository offerRepository = new LocalOfferRepository(offers);

    List<Product> products = List.of(
        new Product("FR1", "Fruit tea", 3.11),
        new Product("SR1", "Strawberries", 5.0),
        new Product("CF1", "Coffee", 11.23)
    );
    ProductRepository productRepository = new LocalProductRepository(products);
    Checkout checkout = new Checkout(offerRepository, productRepository);

    @Test
    void official_test_one() {
        checkout.clear();
        checkout.scan("FR1");
        checkout.scan("SR1");
        checkout.scan("FR1");
        checkout.scan("FR1");
        checkout.scan("CF1");
        assertThat(checkout.total()).isEqualTo(22.45);
    }

    @Test
    void official_test_three() {
        checkout.clear();
        checkout.scan("SR1");
        checkout.scan("SR1");
        checkout.scan("FR1");
        checkout.scan("SR1");
        assertThat(checkout.total()).isEqualTo(16.61);
    }

    @Test
    void scan_illegal_item() {
        checkout.clear();
        ThrowableAssert.ThrowingCallable scanNonexistentItem = () -> checkout.scan("null");
        Assertions.assertThatThrownBy(scanNonexistentItem).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void one_fruit_tea() {
        checkout.clear();
        checkout.scan("FR1");
        assertThat(checkout.total()).isEqualTo(3.11);
    }

    @Test
    void one_strawberries() {
        checkout.clear();
        checkout.scan("SR1");
        assertThat(checkout.total()).isEqualTo(5.00);
    }

    @Test
    void one_coffee() {
        checkout.clear();
        checkout.scan("CF1");
        assertThat(checkout.total()).isEqualTo(11.23);
    }

    @Test
    void two_fruit_teas() {
        checkout.clear();
        checkout.scan("FR1");
        checkout.scan("FR1");
        assertThat(checkout.total()).isEqualTo(3.11);
    }

    @Test
    void two_strawberries() {
        checkout.clear();
        checkout.scan("SR1");
        checkout.scan("SR1");
        assertThat(checkout.total()).isEqualTo(5.00 * 2);
    }

    @Test
    void two_coffees() {
        checkout.clear();
        checkout.scan("CF1");
        checkout.scan("CF1");
        assertThat(checkout.total()).isEqualTo(11.23 * 2);
    }

    @Test
    void naiveTotal() {
        checkout.clear();
        checkout.scan("FR1");
        checkout.scan("SR1");
        checkout.unscan("FR1");
        checkout.scan("FR1");
        checkout.scan("CF1");
        assertThat(checkout.totalWithoutOffers()).isEqualTo(19.34);
    }

    @Test
    void unscan() {
        checkout.clear();
        checkout.scan("SR1");
        checkout.unscan("SR1");
        assertThat(checkout.total()).isEqualTo(0.0);
    }
}
