package com.weather.metrics.stat;

import com.weather.metrics.model.Measurement;
import com.weather.metrics.model.Statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import org.apache.commons.lang3.StringUtils;

import static com.weather.metrics.common.MetricsConstant.TEMPERATURE;

@Component
public class TemparatureStatGenerator implements MetricStatGenerator {

    @Autowired
    private PrecipitationStatGenerator successor;

    @Override
    public void execute(List<Statistic> statisticList, List<Measurement> measurementList, String[] statList, String[] metricsList) {
        for(int i=0;i < metricsList.length; i++){
            if(StringUtils.equalsIgnoreCase(metricsList[i],TEMPERATURE )){
                for(int j=0; j < statList.length ; j++)
                {
                    switch (statList[j]){
                        case "max":
                            statisticList.add(max(measurementList));
                            break;
                        case "min":
                            statisticList.add(min(measurementList));
                            break;
                        case "average":
                            statisticList.add(average(measurementList));
                    }
                }
            }
        }
        successor.execute(statisticList,measurementList,statList,metricsList);
    }

    @Override
       public Statistic max(List<Measurement> measurementList) {
        Statistic statistic = Statistic.builder()
                                       .metric("temperature")
                                       .stat("max")
                                       .value(measurementList.stream()
                                                             .mapToDouble(measurement -> measurement.getTemperature())
                                                             .max()
                                                              .orElse(0.0d))
                                       .build();
        return statistic;
    }

    @Override
    public Statistic min(List<Measurement> measurementList) {
        Statistic statistic = Statistic.builder()
                .metric("temperature")
                .stat("min")
                .value(measurementList.stream()
                        .mapToDouble(measurement -> measurement.getTemperature())
                        .min()
                        .orElse(0.0d))
                .build();
        return statistic;
    }

    @Override
    public Statistic average(List<Measurement> measurementList) {
        Statistic statistic = Statistic.builder()
                .metric("temperature")
                .stat("average")
                .value(measurementList.stream()
                        .mapToDouble(measurement -> measurement.getTemperature())
                        .average()
                        .orElse(0.0d))
                .build();
        return statistic;
    }
}
