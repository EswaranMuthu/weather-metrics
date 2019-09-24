package com.weather.metrics.stat;

import com.weather.metrics.model.Measurement;
import com.weather.metrics.model.Statistic;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import static com.weather.metrics.common.MetricsConstant.PRECIPITATION;

@Component
public class PrecipitationStatGenerator implements MetricStatGenerator {

    @Autowired
    private DewPointGenerator successor;

    @Override
    public void execute(List<Statistic> statisticList, List<Measurement> measurementList, String[] statList, String[] metricsList) {
        for(int i=0;i < metricsList.length; i++){
            if(StringUtils.equalsIgnoreCase(metricsList[i],PRECIPITATION )){
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
                .metric("precipitation")
                .stat("max")
                .value(measurementList.stream()
                        .mapToDouble(measurement -> measurement.getPrecipitation())
                        .max()
                        .orElse(0.0d))
                .build();
        return statistic;
    }

    @Override
    public Statistic min(List<Measurement> measurementList) {
        Statistic statistic = Statistic.builder()
                .metric("precipitation")
                .stat("min")
                .value(measurementList.stream()
                        .mapToDouble(measurement -> measurement.getPrecipitation())
                        .min()
                        .orElse(0.0d))
                .build();
        return statistic;
    }

    @Override
    public Statistic average(List<Measurement> measurementList) {
        Statistic statistic = Statistic.builder()
                .metric("precipitation")
                .stat("average")
                .value(measurementList.stream()
                        .mapToDouble(measurement -> measurement.getPrecipitation())
                        .average()
                        .orElse(0.0d))
                .build();
        return statistic;
    }
}
