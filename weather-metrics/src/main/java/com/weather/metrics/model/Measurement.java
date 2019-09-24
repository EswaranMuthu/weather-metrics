package com.weather.metrics.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.weather.metrics.common.ZonedDateTimeDeserializer;
import com.weather.metrics.common.ZonedDateTimeSerializer;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Builder
public class Measurement {

    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    @JsonDeserialize(using = ZonedDateTimeDeserializer.class)
    private ZonedDateTime timeStamp;
    private double temperature;
    private double precipitation;
    private double windMPH;
    private double humidity;
    private double dewPoint;

}
