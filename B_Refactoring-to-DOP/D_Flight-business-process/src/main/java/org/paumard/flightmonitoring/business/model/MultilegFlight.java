package org.paumard.flightmonitoring.business.model;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public record MultilegFlight(MultilegFlightID id, City from, City via, City to)
        implements Flight {

    private static final Map<MultilegFlightID, Price> pricePerFlight =
            new ConcurrentHashMap<>();

    public static Price price(MultilegFlightID flightId) {
        return pricePerFlight.get(flightId);
    }

    public static void updatePrice(MultilegFlightID id, Price price) {
        pricePerFlight.put(id, price);
    }

    public MultilegFlight {
        Objects.requireNonNull(id);
        Objects.requireNonNull(from);
        Objects.requireNonNull(via);
        Objects.requireNonNull(to);
    }
}
