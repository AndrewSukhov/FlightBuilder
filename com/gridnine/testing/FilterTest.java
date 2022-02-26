package com.gridnine.testing;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FilterTest {
    Filter filter = new Filter();
    LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);
    List<Flight> actual = new ArrayList<>();
    List<Segment> segments;

    @Before
    public void setUp() {
        segments = new ArrayList<>();
    }

    // Убрать вылеты до текущего момента времени
    @Test
    public void flightsBeforeNow() {
        segments.add(new Segment(threeDaysFromNow.minusDays(6), threeDaysFromNow));
        Flight flightDepartingInThePast = new Flight(segments);
        List<Flight> flightList = new ArrayList<>();
        flightList.add(flightDepartingInThePast);
        List<Flight> expected = filter.flightsBeforeNow(flightList);
        Assert.assertEquals(expected, actual);
    }

    // Убрать перелеты там где сегменты с датой прилёта раньше даты вылета
    @Test
    public void secondSegmentBeforeFirst() {
        segments.add(new Segment(threeDaysFromNow, threeDaysFromNow.minusHours(6)));
        Flight flightDepartsBeforeItArrives = new Flight(segments);
        List<Flight> flightList = new ArrayList<>();
        flightList.add(flightDepartsBeforeItArrives);
        List<Flight> expected = filter.secondSegmentBeforeFirst(flightList);
        Assert.assertEquals(expected, actual);
    }

    // Убрать перелеты там где общее время, проведённое на земле, превышает два часа
    @Test
    public void groundTimeMoreThenTwoHours() {
        segments.add(new Segment(threeDaysFromNow, threeDaysFromNow.plusHours(2)));
        segments.add(new Segment(threeDaysFromNow.plusHours(5), threeDaysFromNow.plusHours(6)));
        Flight flightWithMoreThanTwoHoursGroundTime = new Flight(segments);
        List<Flight> flightList = new ArrayList<>();
        flightList.add(flightWithMoreThanTwoHoursGroundTime);
        List<Flight> expected = filter.groundTimeMoreThenTwoHours(flightList);
        Assert.assertEquals(expected, actual);
    }
}