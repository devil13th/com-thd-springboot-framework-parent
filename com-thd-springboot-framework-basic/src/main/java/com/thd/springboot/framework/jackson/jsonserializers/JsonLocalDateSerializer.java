package com.thd.springboot.framework.jackson.jsonserializers;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

//时间序列化时变为时间戳
public class JsonLocalDateSerializer extends JsonSerializer<LocalDate> {
    @Override
    public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        ser(localDate,jsonGenerator,serializerProvider,null);
    }

    @Override
    public void serializeWithType(LocalDate value, JsonGenerator gen, SerializerProvider serializers, TypeSerializer typeSer) throws IOException {
        ser(value,gen,serializers,typeSer);
    }

    private void ser(LocalDate value, JsonGenerator gen, SerializerProvider serializers, TypeSerializer typeSer) throws IOException {
        gen.writeNumber(value.atStartOfDay(ZoneOffset.ofHours(8)).toInstant().toEpochMilli());
    }
}