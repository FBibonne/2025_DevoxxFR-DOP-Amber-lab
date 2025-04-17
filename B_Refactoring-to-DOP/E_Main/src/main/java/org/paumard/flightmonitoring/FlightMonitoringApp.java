package org.paumard.flightmonitoring;

import org.paumard.flightmonitoring.business.FlightMonitoring;
import org.paumard.flightmonitoring.db.FlightDBService;
import org.paumard.flightmonitoring.gui.FlightGUI;
import org.paumard.flightmonitoring.model.FlightID;
import org.paumard.flightmonitoring.pricemonitoring.FlightPriceMonitoringService;
import org.paumard.flightmonitoring.service.DBService;
import org.paumard.flightmonitoring.service.FlightGUIService;
import org.paumard.flightmonitoring.service.PriceMonitoringService;

public class FlightMonitoringApp {

    public void main() {

        DBService dbService =
                new FlightDBService();
        FlightGUIService guiService =
                new FlightGUI();
        PriceMonitoringService monitoringService =
                new FlightPriceMonitoringService();
        var flightMonitoring =
                new FlightMonitoring(
                        dbService,monitoringService, guiService);

        var f1 = new FlightID("PaAt"); // Paris Atlanta
        var f2 = new FlightID("AmNY"); // Amsterdam New York
        var f3 = new FlightID("LoMi"); // London Miami
        var f4 = new FlightID("FrWa"); // Frankurt Washington

        flightMonitoring.followFlight(f1);
        flightMonitoring.followFlight(f2);
        flightMonitoring.followFlight(f3);
        flightMonitoring.followFlight(f4);

        flightMonitoring.monitorFlight(f1);
        flightMonitoring.monitorFlight(f2);
        flightMonitoring.monitorFlight(f3);
        flightMonitoring.monitorFlight(f4);

        monitoringService.updatePrices();
        flightMonitoring.launchDisplay();

        while (true) {

        }
    }
}