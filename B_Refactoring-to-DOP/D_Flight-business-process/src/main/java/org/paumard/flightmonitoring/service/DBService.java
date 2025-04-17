package org.paumard.flightmonitoring.service;

import org.paumard.flightmonitoring.model.Flight;
import org.paumard.flightmonitoring.model.FlightID;

public interface DBService {
    Flight fetchFlight(FlightID flightID);
}
