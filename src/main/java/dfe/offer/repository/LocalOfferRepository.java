package dfe.offer.repository;

import dfe.offer.Offer;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class LocalOfferRepository implements OfferRepository {
    private Map<String, Offer> offers;

    public LocalOfferRepository(List<Offer> offerList) {
        offers = offerList.stream().collect(Collectors.toMap(Offer::getID, offer -> offer));
    }

    @Override
    public Optional<Offer> getOfferByItemID(String itemId) {
        return Optional.ofNullable(offers.getOrDefault(itemId, null));
    }
}
