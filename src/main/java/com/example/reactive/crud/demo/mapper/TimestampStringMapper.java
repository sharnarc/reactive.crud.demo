package com.example.reactive.crud.demo.mapper;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.Optional;

/**
 * <code>TimestampStringMapper</code> is mapper to map timestamp to string vice-versa
 *
 * @author SUMANPattanaik
 */
public class TimestampStringMapper {
    /**
     * Convert timestamp string to timestamp
     *
     * @param timestampString String
     * @return Timestamp
     */
    Timestamp toTimestamp(String timestampString) {
        return Optional.ofNullable(timestampString)
                .filter(timestamp -> !timestamp.isEmpty())
                .map(OffsetDateTime::parse)
                .map(OffsetDateTime::toLocalDateTime)
                .map(Timestamp::valueOf)
                .orElse(null);
    }

    /**
     * Convert timestamp to string
     *
     * @param timestamp Timestamp
     * @return String
     */
    public String toString(Timestamp timestamp) {
        return timestamp.toString();
    }
}
