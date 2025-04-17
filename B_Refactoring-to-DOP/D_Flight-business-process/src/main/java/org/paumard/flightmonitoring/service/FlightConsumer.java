package org.paumard.flightmonitoring.service;

import org.paumard.flightmonitoring.model.Price;

public interface FlightConsumer {

    void updateFlight(Price price);
}
