package dfe.offer.repository;

import dfe.offer.Offer;

public interface OfferRepository {
    Offer getOfferByItemID(String itemId);
}
