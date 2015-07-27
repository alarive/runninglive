package com.runninglive.util;

import javax.persistence.AttributeConverter;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * User: alarive
 */
public class TimePersistenceConverter implements AttributeConverter<Duration, Long> {

    @Override
    public Long convertToDatabaseColumn(Duration entityValue) {
        return entityValue.getSeconds();
    }

    @Override
    public Duration convertToEntityAttribute(Long databaseValue) {
        return Duration.of(databaseValue, ChronoUnit.SECONDS);
    }
}
