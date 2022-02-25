package com.gridnine.testing;

public class Main {
    public static void main(String[] args) {
        for (var flight : FlightBuilder.createFlights()) {
            System.out.println(flight);
        }
    }
}
