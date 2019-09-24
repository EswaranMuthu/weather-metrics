package com.weather.metrics.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.regex.Pattern;

@Slf4j
public class ZonedDateTimeDeserializer extends StdDeserializer<ZonedDateTime> {
    public ZonedDateTimeDeserializer() {
        super(ZonedDateTime.class);
    }

    @Override
    public ZonedDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String dateTimeInUTC = p.getText();
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateTimeInUTC);
        return zonedDateTime;
    }



    public static void main(String args[]){
        System.out.println(Pattern.matches(".s", "as"));//true (2nd char is s)

    }
}
