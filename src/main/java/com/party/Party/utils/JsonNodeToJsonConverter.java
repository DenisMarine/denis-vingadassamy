package com.party.Party.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class JsonNodeToJsonConverter implements Converter<JsonNode, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convert(JsonNode source) {
        if (source != null) {
            try {
                return objectMapper.writeValueAsString(source);
            } catch (Exception e) {
                throw new IllegalStateException("Error converting JsonNode to JSON string", e);
            }
        }
        return null;
    }
}