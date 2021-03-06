package com.thd.springboot.framework.jackson.jsondeserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.thd.springboot.framework.utils.DateUtils;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * 时间戳序列化器
 *
 * @author: wanglei62
 * @DATE: 2020/4/1 11:11
 **/
public class JsonTimestampDeserializer extends JsonDeserializer<Timestamp> {

    @Override
    public Timestamp deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return der(jsonParser,deserializationContext,null);
    }

    @Override
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        return der(jsonParser,ctxt,typeDeserializer);
    }

    private Timestamp der(JsonParser jsonParser, DeserializationContext context, TypeDeserializer typeDeserializer) throws IOException {
        String dateStr = jsonParser.getText();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Timestamp d = DateUtils.stringToTimestamp(dateStr);
            return d;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
