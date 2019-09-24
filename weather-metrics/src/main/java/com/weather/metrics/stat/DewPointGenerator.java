package com.weather.metrics.stat;

import com.weather.metrics.model.Measurement;
import com.weather.metrics.model.Statistic;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import static com.weather.metrics.common.MetricsConstant.DEW_POINT;

@Component
public class DewPointGenerator implements MetricStatGenerator {

    @Autowired
    DewPointGenerator successor;

    @Override
    public void execute(List<Statistic> statisticList, List<Measurement> measurementList, String[] statList, String[]metricsList) {
        for(int i=0;i < metricsList.length; i++){
            if(StringUtils.equalsIgnoreCase(metricsList[i],DEW_POINT )){
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
       // successor.execute(statisticList,measurementList,statList,metricsList);
    }

    @Override
    public Statistic max(List<Measurement> measurementList) {
        Statistic statistic = Statistic.builder()
                .metric("temperature")
                .stat("max")
                .value(measurementList.stream()
                        .mapToDouble(measurement -> measurement.getDewPoint())
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
                        .mapToDouble(measurement -> measurement.getDewPoint())
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
                        .mapToDouble(measurement -> measurement.getDewPoint())
                        .average()
                        .orElse(0.0d))
                .build();
        return statistic;
    }

}
