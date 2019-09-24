package com.weather.metrics.controller;

import com.weather.metrics.model.Measurement;
import com.weather.metrics.model.Statistic;
import com.weather.metrics.service.WeatherMetricsManager;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.time.ZonedDateTime;
import java.util.List;

@RestController
@Slf4j

public class WeatherMetricsController {

    @Autowired
    private WeatherMetricsManager weatherMetricsManager;

    @PostMapping(value = "/measurements", produces = "application/json")
    @ApiOperation(value = "Save weather metrics from IOT ", notes = "Gets item details for single part number")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "CREATED"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public ResponseEntity saveMeasurement(@RequestBody Measurement measurement){

        log.info("Input for saveMeasurement {}", measurement);
        if(measurement == null || measurement.getTimeStamp() == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        weatherMetricsManager.saveMeasurement(measurement);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping(value="/measurements/timeStamp/{timeStamp}", produces = "application/json")
    @ApiOperation(value = "Get weather measurement for given timestamp ", notes = "Validate timestamp, and return measurement for given timestamp")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 422, message = "Input is not valid")
    })
    public ResponseEntity<Measurement> getMeasurement(@PathVariable String timeStamp){
        log.info("Get measurement for {} timestamp ", timeStamp);
        if(timeStamp == null)
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        Measurement measurement = weatherMetricsManager.getMeasurement(timeStamp);
        if(measurement == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(measurement,HttpStatus.OK);
    }


    @GetMapping(value="/measurements/date/{date}", produces = "application/json")
    @ApiOperation(value = "Get weather measurement list for given date ", notes = "Validate Date, and return measurement for given timestamp")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 422, message = "Input is not valid")
    })
    public ResponseEntity<List<Measurement>> getMeasurementsForDate(@PathVariable String date){
        log.info("Get measurement for {} timestamp ", date);
        if(date == null)
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        List<Measurement> measurementList = weatherMetricsManager.getMeasurementForGivenDate(date);
        if(measurementList == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(measurementList,HttpStatus.OK);
    }

    @DeleteMapping(value="/measurements/timeStamp/{timeStamp}", produces = "application/json")
    @ApiOperation(value = "delete weather measurement for given timeStamp ", notes = "Validate timeStamp, and delete measurement from given timeStamp")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success"),
            @ApiResponse(code = 422, message = "Input is not valid, Time stamp is null")
    })
    public ResponseEntity deleteMeasurement(@PathVariable String timeStamp){
        log.info("Get measurement for {} timestamp ", timeStamp);
        if(timeStamp == null)
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        weatherMetricsManager.deleteMeasurement(timeStamp);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping(value="/stats" , produces = "application/json")
    @ApiOperation(value = "Get stats for a well-reported metric", notes = "Get stats for a well-reported metric")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success"),
            @ApiResponse(code = 422, message = "Input is not valid, Time stamp is null")
    })
    public ResponseEntity<List<Statistic>> getStats(@RequestParam String[] statArray, @RequestParam String[] metricArray,
                                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime fromDateTime,
                                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime toDateTime ) {

        log.info("from - to dateTime input for getStats() : {} & {}", fromDateTime, toDateTime);

        if( statArray == null || metricArray == null || statArray.length != metricArray.length){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Statistic> statisticList = weatherMetricsManager.getStats(statArray,metricArray,fromDateTime, toDateTime);
        if(CollectionUtils.isEmpty(statisticList))
            return new ResponseEntity<>(HttpStatus. NO_CONTENT);
        return new ResponseEntity<>(statisticList, HttpStatus.OK);
    }

}
