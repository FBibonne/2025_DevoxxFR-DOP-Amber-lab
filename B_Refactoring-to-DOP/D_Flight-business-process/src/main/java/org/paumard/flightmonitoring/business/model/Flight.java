package org.paumard.flightmonitoring.business.model;

public sealed interface Flight
        permits MultilegFlight, SimpleFlight {}