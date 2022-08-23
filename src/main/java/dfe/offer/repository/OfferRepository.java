package dfe.offer.repository;

import dfe.offer.Offer;

import java.util.Optional;

public interface OfferRepository {
    Optional<Offer> getOfferByItemID(String itemId);
}
