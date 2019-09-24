package com.weather.metrics.service;

import com.weather.metrics.model.InMemoryStorage;
import com.weather.metrics.model.Measurement;
import com.weather.metrics.model.Statistic;
import com.weather.metrics.stat.TemparatureStatGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class WeatherMetricsManager {

    @Autowired
    TemparatureStatGenerator metricStatGenerator;

    /**
     * This method will store measurement in memory storage
     *
     * @param measurement
     */
    public void saveMeasurement(Measurement measurement){
        InMemoryStorage inMemoryStorage = InMemoryStorage.getInstance();
        String dateTimeInUTC = measurement.getTimeStamp().toOffsetDateTime().toString();
        inMemoryStorage.getMeasurements().put(dateTimeInUTC, measurement);
    }

    /**
     * This method will fetch measurement for given date
     * @param date
     * @return
     */
    public List<Measurement> getMeasurementForGivenDate(String date){
        InMemoryStorage inMemoryStorage = InMemoryStorage.getInstance();
        Map<String, Measurement> measurementMap =  inMemoryStorage.getMeasurements();
        List<Measurement> measurementList = Optional.ofNullable(measurementMap).orElseGet(Collections::emptyMap)
                .entrySet()
                .stream()
                .filter(e -> String.valueOf(e.getKey()).contains(date))
                .map(e -> e.getValue())
                .collect(Collectors.toList());
        return measurementList;
    }

    /**
     * This method will fetch measurement for given timeStamp
     * @param timeStamp
     * @return
     */
    public Measurement getMeasurement(String timeStamp){
        InMemoryStorage inMemoryStorage = InMemoryStorage.getInstance();
        return inMemoryStorage.getMeasurements().get(timeStamp);
     }

    /**
     * This method will delete measurement for given timeStamp
     * @param timeStamp
     */
    public void deleteMeasurement(String timeStamp){
        log.info("Measurement for {} timeStamp will be deleted", timeStamp);
        InMemoryStorage inMemoryStorage = InMemoryStorage.getInstance();
        Measurement measurement = inMemoryStorage.getMeasurements().remove(timeStamp);
        if(measurement == null)
            log.info("Record not found for given timeStamp");
        log.info("record successfully deleted ");
    }

    public List<Statistic> getStats(String[] statsArray, String[] metricsArray, ZonedDateTime fromDateTime, ZonedDateTime toDateTime){

        InMemoryStorage inMemoryStorage = InMemoryStorage.getInstance();
        List<Measurement> measurementList = getMeasurementInGivenDateRange(inMemoryStorage.getMeasurements(), fromDateTime, toDateTime);
        List<Statistic> statisticList = new ArrayList<>();
        metricStatGenerator.execute(statisticList,measurementList,statsArray,metricsArray );
        return statisticList;
    }

    /**
     * This method will get list of measurement in given date range
     * @param measurementMap
     * @param fromDateTime
     * @param toDateTime
     * @return
     */
    private List<Measurement> getMeasurementInGivenDateRange(Map<String, Measurement> measurementMap , ZonedDateTime fromDateTime, ZonedDateTime toDateTime){
        return Optional.ofNullable(measurementMap).orElseGet(Collections::emptyMap)
                .entrySet()
                .stream()
                .filter(e-> (e.getValue().getTimeStamp().isBefore(toDateTime)
                              && e.getValue().getTimeStamp().isAfter(fromDateTime)))
                .map(e -> e.getValue())
                .collect(Collectors.toList());
    }

}
