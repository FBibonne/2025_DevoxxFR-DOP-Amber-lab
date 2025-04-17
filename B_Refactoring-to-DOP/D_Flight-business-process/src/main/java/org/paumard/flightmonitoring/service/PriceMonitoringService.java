package org.paumard.flightmonitoring.service;

import org.paumard.flightmonitoring.model.FlightID;

public interface PriceMonitoringService {
    void followPrice(FlightID flightPK, FlightConsumer consumer);

    void updatePrices();
}
