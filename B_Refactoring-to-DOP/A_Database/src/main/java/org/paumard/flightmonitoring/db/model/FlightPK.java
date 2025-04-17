package org.paumard.flightmonitoring.db.model;

public sealed interface FlightPK
        permits MultilegFlightPK, SimpleFlightPK {}
