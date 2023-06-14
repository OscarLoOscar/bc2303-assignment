package com.codewave.project.crypto.coingecko.config;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CustomLocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    private static final DateTimeFormatter[] FORMATTERS = {
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
    };

    @Override
    public LocalDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        String dateTimeStr = parser.getText();
        for (DateTimeFormatter formatter : FORMATTERS) {
            try {
                if (dateTimeStr.endsWith("Z")) {
                    // Parse as LocalDateTime for "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
                    return LocalDateTime.parse(dateTimeStr, formatter);
                } else {
                    return LocalDateTime.parse(dateTimeStr.substring(0, 19), formatter);
                }
            } catch (Exception ignored) {
            }
        }
        throw new IllegalArgumentException("Invalid date-time format: " + dateTimeStr);
    }
}
