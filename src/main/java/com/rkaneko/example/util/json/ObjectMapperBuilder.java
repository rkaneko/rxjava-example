package com.rkaneko.example.util.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.base.Preconditions;

public class ObjectMapperBuilder {
    private final ObjectMapper mapper;

    public ObjectMapperBuilder() {
        mapper = new ObjectMapper();
    }

    public ObjectMapperBuilder(ObjectMapper mapper) {
        Preconditions.checkNotNull(mapper);
        this.mapper = mapper;
    }

    public ObjectMapperBuilder configure() {
        mapper.enable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return this;
    }

    public ObjectMapperBuilder withOptionalModule() {
        mapper.registerModule(new Jdk8Module());
        return this;
    }

    public ObjectMapperBuilder withJavaTimeModule() {
        mapper.registerModule(new JavaTimeModule());
        return this;
    }

    public ObjectMapperBuilder disable(DeserializationFeature feature) {
        mapper.disable(feature);
        return this;
    }

    public ObjectMapperBuilder disable(SerializationFeature feature) {
        mapper.disable(feature);
        return this;
    }

    public ObjectMapperBuilder enable(DeserializationFeature feature) {
        mapper.enable(feature);
        return this;
    }

    public ObjectMapperBuilder enable(SerializationFeature feature) {
        mapper.enable(feature);
        return this;
    }

    public ObjectMapper build() {
        return mapper;
    }
}
