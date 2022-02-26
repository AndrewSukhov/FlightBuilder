package com.gridnine.testing;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Filter filter = new Filter();
        List<Flight> flights = FlightBuilder.createFlights();
        List<Flight> flightsBeforeNow = filter.flightsBeforeNow(flights);
        List<Flight> secondSegmentBeforeFirst = filter.secondSegmentBeforeFirst(flights);
        List<Flight> groundTimeMoreThenTwoHours = filter.groundTimeMoreThenTwoHours(flights);

        System.out.println("Вывод всех перелетов");
        printFlights(flights);
        System.out.println("Убрать вылеты до текущего момента времени");
        printFlights(flightsBeforeNow);
        System.out.println("Убрать перелеты там где сегменты с датой прилёта раньше даты вылета");
        printFlights(secondSegmentBeforeFirst);
        System.out.println("Убрать перелеты там где общее время, проведённое на земле, превышает два часа");
        printFlights(groundTimeMoreThenTwoHours);

    }

    static void printFlights(List<Flight> flights) {
        for (var flight : flights) {
            System.out.println(flight);
        }
        System.out.println("🛸 🛸 🛸");
    }
}
