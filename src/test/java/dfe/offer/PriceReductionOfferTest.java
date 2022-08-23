package dfe.offer;

import dfe.product.Product;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PriceReductionOfferTest {
    Product honey = new Product("H1", "Honey", 2.50);
    PriceReductionOffer priceReductionOffer = new PriceReductionOffer(honey.getId(), 4, 2);

    @Test
    void minimum_not_reached() {
        assertThat(priceReductionOffer.calculatePrice(honey, 3)).isEqualTo(2.50 * 3);
    }

    @Test
    void minimum_reached() {
        assertThat(priceReductionOffer.calculatePrice(honey, 4)).isEqualTo(2 * 4);
    }
}
