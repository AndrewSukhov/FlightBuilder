package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Класс Filter содержит набор методов, отсеивающих список полетов по определенным критериям.
public class Filter {
    // Убрать вылеты до текущего момента времени
    public List<Flight> flightsBeforeNow(List<Flight> flights) {
        List<Flight> listFlights = new ArrayList<>(flights);
        LocalDateTime now = LocalDateTime.now();
        for (Iterator<Flight> iterator = listFlights.iterator(); iterator.hasNext(); ) {
            Flight flight = iterator.next();
            for (Segment segment : flight.getSegments()) {
                if (segment.getDepartureDate().isBefore(now)) {
                    iterator.remove();
                }
            }
        }
        return listFlights;
    }

    // Убрать перелеты там где сегменты с датой прилёта раньше даты вылета
    public List<Flight> secondSegmentBeforeFirst(List<Flight> flights) {
        List<Flight> listFlights = new ArrayList<>(flights);
        for (Iterator<Flight> iterator = listFlights.iterator(); iterator.hasNext(); ) {
            Flight flight = iterator.next();
            for (Segment segment : flight.getSegments()) {
                if (segment.getDepartureDate().isAfter(segment.getArrivalDate())) {
                    iterator.remove();
                }
            }
        }
        return listFlights;
    }

    // Убрать перелеты там где общее время, проведённое на земле, превышает два часа
    public List<Flight> groundTimeMoreThenTwoHours(List<Flight> flights) {
        List<Flight> listFlights = new ArrayList<>(flights);
        for (Iterator<Flight> iterator = listFlights.iterator(); iterator.hasNext(); ) {
            Flight flight = iterator.next();
            long duration = 0;
            for (int i = 1; i < flight.getSegments().size(); i++) {
                LocalDateTime segment1 = flight.getSegments().get(i - 1).getArrivalDate();
                LocalDateTime segment2 = flight.getSegments().get(i).getDepartureDate();
                duration += Duration.between(segment1, segment2).toMinutes();
                if (duration > 120) {
                    iterator.remove();
                }
            }
        }
        return listFlights;
    }
}
