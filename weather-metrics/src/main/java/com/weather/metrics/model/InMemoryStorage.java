package com.weather.metrics.model;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class InMemoryStorage {

    // Initialization-on-demand holder idiom
    private static class InMemoryStorageLazyHolder
    {
        private static final InMemoryStorage INSTANCE = new InMemoryStorage();
    }

    public static InMemoryStorage getInstance(){
        return InMemoryStorageLazyHolder.INSTANCE;
    }

    private Map<String, Measurement> measurements = new ConcurrentHashMap<>()   ;

    private Measurement getMeasurement(String timmeStamp){
        return null;
    }

    private void saveMeasurement(Measurement measurement){


    }

    private Boolean deleteMeasurement(String timeStamp){
        return null;
    }


}
