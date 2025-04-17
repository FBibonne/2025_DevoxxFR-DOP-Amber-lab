package org.paumard.flightmonitoring.business;

import org.paumard.flightmonitoring.model.Flight;
import org.paumard.flightmonitoring.model.FlightID;
import org.paumard.flightmonitoring.service.DBService;
import org.paumard.flightmonitoring.service.FlightConsumer;
import org.paumard.flightmonitoring.service.FlightGUIService;
import org.paumard.flightmonitoring.service.PriceMonitoringService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FlightMonitoring {

    private static final Map<FlightID, Flight> monitoredFlights = new ConcurrentHashMap<>();

    private final DBService dbService;
    private final PriceMonitoringService priceMonitoringService;
    private final FlightGUIService flightGUIService ;

    public FlightMonitoring(DBService dbService, PriceMonitoringService priceMonitoringService, FlightGUIService flightGUIService) {
        this.dbService = dbService;
        this.priceMonitoringService = priceMonitoringService;
        this.flightGUIService = flightGUIService;
    }

    public void followFlight(FlightID flightID) {
        Flight flightEntity = dbService.fetchFlight(flightID);
        FlightConsumer flightConsumer = flightEntity::updatePrice;
        priceMonitoringService.followPrice(flightID, flightConsumer);
    }

    public void monitorFlight(FlightID flightID) {
        monitoredFlights.put(flightID, dbService.fetchFlight(flightID));
    }

    public void launchDisplay() {
        var executor = Executors.newScheduledThreadPool(1);
        Runnable task = () -> {
            System.out.println("Displaying " + monitoredFlights.size() + " flights");
            for (var flight : monitoredFlights.values()) {
                flightGUIService.displayFlight(flight);
            }
        };
        executor.scheduleAtFixedRate(task, 0, 500, TimeUnit.MILLISECONDS);
    }
}