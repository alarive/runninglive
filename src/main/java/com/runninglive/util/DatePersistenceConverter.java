package com.runninglive.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * User: alarive
 */
@Converter(autoApply = true)
public class DatePersistenceConverter implements AttributeConverter<LocalDateTime, Timestamp> {

    public java.sql.Timestamp convertToDatabaseColumn(LocalDateTime entityValue) {
        return java.sql.Timestamp.valueOf(entityValue);
    }

    public LocalDateTime convertToEntityAttribute(java.sql.Timestamp databaseValue) {
        return databaseValue.toLocalDateTime();
    }
}