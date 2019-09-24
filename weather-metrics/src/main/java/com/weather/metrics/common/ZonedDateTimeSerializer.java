package com.weather.metrics.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.time.ZonedDateTime;

/**
 * This converters provide support to converts ZonedDate into SQL timestamp.
 */
@Slf4j
public class ZonedDateTimeSerializer extends StdSerializer<ZonedDateTime> {

    public ZonedDateTimeSerializer() {
        super(ZonedDateTime.class);
    }
    @Override
    public void serialize(ZonedDateTime value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        String dateTimeInUTC = (value == null ? null : value.toOffsetDateTime().toString());
        log.debug("The ZonedDateTime serialized to ISO-8061 string in UTC - {} ", dateTimeInUTC);
        gen.writeString(dateTimeInUTC);
    }
}
