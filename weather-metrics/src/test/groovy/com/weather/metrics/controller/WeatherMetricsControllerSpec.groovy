package com.weather.metrics.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.weather.metrics.model.Measurement
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Shared
import spock.lang.Specification

import java.time.ZonedDateTime

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class WeatherMetricsControllerSpec  extends Specification {

    @Shared
    MockMvc mockMvc

    def setupSpec() {
        mockMvc = MockMvcBuilders.standaloneSetup(WeatherMetricsController).build()

    }

    def "valid save measurement - Response with 4XX status"(){
        given:
        def measurement = measurementData()
        def json = new ObjectMapper().writeValueAsString(measurement)
        System.out.println(json)
        expect:
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/measurements")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(measurement)))
                        .andExpect(status().is4xxClientError())
    }

    def "invalid save measurement - response with 4XX status"(){
        given:
        def measurement = null
        expect:
        mockMvc.perform(MockMvcRequestBuilders
                .post("/measurement")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(measurement)))
                .andExpect(status().is4xxClientError())
    }

    def "invalid getMeasurement with timeStamp - response with 4xx status"(){
        given:
        expect:
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/measurement/timeStamp/")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().is4xxClientError())
    }

    def "invalid  deleteMeasurement with timeStamp - response with 4XX status"(){
        expect:
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/measurement/timeStamp/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
    }



    private static  Measurement measurementData(){
        ZonedDateTime.parse("2019-09-20T19:33:39Z")
        def measurement = Measurement.builder().build()
        measurement.setDewPoint(2.2d)
        measurement.setTemperature(72.3d)
        measurement.setHumidity(70.8d)
        measurement.setTimeStamp(ZonedDateTime.parse("2019-09-20T19:33:39Z"))
        return measurement
    }

}
