package com.thd.springboot.framework.jackson.jsondeserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.thd.springboot.framework.utils.DateUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

//时间戳反序列化时间
public class JsonLocalDateDeserializer extends JsonDeserializer<LocalDate> {
    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return der(jsonParser,deserializationContext,null);
    }

    @Override
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        return der(jsonParser,ctxt,typeDeserializer);
    }

    private LocalDate der(JsonParser jsonParser, DeserializationContext context, TypeDeserializer typeDeserializer) throws IOException {
        String dateStr = jsonParser.getText();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            LocalDate d = DateUtils.stringToLocalDate(dateStr);
            return d;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}