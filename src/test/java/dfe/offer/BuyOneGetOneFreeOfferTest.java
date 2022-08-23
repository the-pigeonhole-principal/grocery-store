package dfe.offer;

import dfe.product.Product;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BuyOneGetOneFreeOfferTest {

    Product honey = new Product("H1", "Honey", 2.50);
    BuyOneGetOneFreeOffer buyOneGetOneFreeOffer = new BuyOneGetOneFreeOffer(honey.getId());

    @Test
    void zero_items() {
        assertThat(buyOneGetOneFreeOffer.calculatePrice(honey, 0)).isEqualTo(0.0);
    }

    @Test
    void one_items() {
        assertThat(buyOneGetOneFreeOffer.calculatePrice(honey, 1)).isEqualTo(2.5);
    }

    @Test
    void two_items() {
        assertThat(buyOneGetOneFreeOffer.calculatePrice(honey, 2)).isEqualTo(2.5);
    }

    @Test
    void three_items() {
        assertThat(buyOneGetOneFreeOffer.calculatePrice(honey, 3)).isEqualTo(5.0);
    }

    @Test
    void four_items() {
        assertThat(buyOneGetOneFreeOffer.calculatePrice(honey, 4)).isEqualTo(5.0);
    }

    @Test
    void five_items() {
        assertThat(buyOneGetOneFreeOffer.calculatePrice(honey, 5)).isEqualTo(7.5);
    }
}
