package com.weather.metrics.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Statistic {
    private String metric;
    private String stat;
    private double value;
}
