package com.weather.metrics.service

import com.weather.metrics.model.InMemoryStorage
import com.weather.metrics.model.Measurement
import com.weather.metrics.stat.DewPointGenerator
import com.weather.metrics.stat.PrecipitationStatGenerator
import com.weather.metrics.stat.TemparatureStatGenerator
import io.micrometer.core.instrument.Statistic
import spock.lang.Specification

import java.time.ZonedDateTime

class WeatherMetricsManagerSpec extends Specification {

    def "get Measurement for given date"(){
        given:
        WeatherMetricsManager weatherMetricsManager = new WeatherMetricsManager()

        InMemoryStorage inMemoryStorage = InMemoryStorage.getInstance()
        inMemoryStorage.getMeasurements().put("2019-09-20T19:33:39Z", Measurement.builder().build())
        inMemoryStorage.getMeasurements().put("2019-09-21T05:33:39Z", Measurement.builder().build())
        inMemoryStorage.getMeasurements().put("2019-09-21T09:33:39Z", Measurement.builder().build())
        inMemoryStorage.getMeasurements().put("2019-09-21T10:33:39Z", Measurement.builder().build())

        when:
        List<Measurement> measurementList = weatherMetricsManager.getMeasurementForGivenDate("2019-09-21")
        then:
        measurementList.size() == 3
    }

    def "get stat for given date range "(){

        given :
        WeatherMetricsManager weatherMetricsManager = new WeatherMetricsManager()
        TemparatureStatGenerator temparatureStatGenerator = new TemparatureStatGenerator()
        PrecipitationStatGenerator precipitationStatGenerator = new PrecipitationStatGenerator()
        DewPointGenerator dewPointGenerator = new DewPointGenerator()
        temparatureStatGenerator.successor = precipitationStatGenerator
        precipitationStatGenerator.successor = dewPointGenerator
        weatherMetricsManager.metricStatGenerator = temparatureStatGenerator


        InMemoryStorage inMemoryStorage = InMemoryStorage.getInstance()
        inMemoryStorage.getMeasurements().put("2019-09-20T19:33:39Z", getMeasurement())
        inMemoryStorage.getMeasurements().put("2019-09-21T05:33:39Z", getMeasurement1())
        inMemoryStorage.getMeasurements().put("2019-09-21T09:33:39Z", getMeasurement2())
        inMemoryStorage.getMeasurements().put("2019-09-21T10:33:39Z", getMeasurement3())

        String[] stat = new String[3]
        stat[0] = "min"
        stat[1] = "average"
        stat[2] = "max"

        String[] metrics = new String[1]
        metrics[0] = "temperature"

        ZonedDateTime fromDateTime = ZonedDateTime.parse("2019-09-20T19:33:39Z")
        ZonedDateTime toDateTime = ZonedDateTime.parse("2019-09-23T19:33:39Z")

        when:
        List<Statistic> statisticList = weatherMetricsManager.getStats(stat, metrics,fromDateTime, toDateTime  )
        then:
        statisticList != null
        statisticList.size() == 3
    }

    Measurement getMeasurement(){
        return Measurement.builder()
                .temperature(45.4d)
                .precipitation(43.4d)
                .timeStamp(ZonedDateTime.parse("2019-09-20T19:33:39Z"))
                .build()
    }

    Measurement getMeasurement1(){
        return Measurement.builder()
                .temperature(35.4d)
                .precipitation(43.4d)
        .timeStamp(ZonedDateTime.parse("2019-09-21T05:33:39Z"))
                .build()
    }

    Measurement getMeasurement2(){
        return Measurement.builder()
                .temperature(25.4d)
                .precipitation(43.4d)
        .timeStamp(ZonedDateTime.parse("2019-09-21T09:33:39Z"))
                .build()
    }

    Measurement getMeasurement3(){
        return Measurement.builder()
                .temperature(15.4d)
                .precipitation(43.4d)
        .timeStamp(ZonedDateTime.parse("2019-09-21T10:33:39Z"))
                .build()
    }

}
