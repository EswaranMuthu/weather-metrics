package com.weather.metrics.stat;

import com.weather.metrics.model.Measurement;
import com.weather.metrics.model.Statistic;

import java.util.List;


/**
 * MetricStatGenerator will populate "Statistic" list based on given input (statList & MetricList)
 * Using "CHAIN OF RESPONSIBILITY - Design pattern"
 */
public interface MetricStatGenerator {
    void execute(List<Statistic> statisticList, List<Measurement> measurementList, String[] statsList, String[] metricsList);

    Statistic max(List<Measurement> measurementList);

    Statistic min(List<Measurement> measurementList);

    Statistic average(List<Measurement> measurementList);
}
