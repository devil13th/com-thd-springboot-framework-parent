package com.thd.springboot.framework.jackson.jsondeserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.thd.springboot.framework.utils.DateUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonDateDeserializer extends JsonDeserializer<Date> {
    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return der(jsonParser, deserializationContext, null);
    }

    @Override
    public Object deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        return der(p, ctxt, typeDeserializer);
    }

    private Date der(JsonParser jsonParser, DeserializationContext context, TypeDeserializer typeDeserializer) throws IOException {
        String dateStr = jsonParser.getText();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
//            Date d = sdf.parse(dateStr);
            Date d = DateUtils.stringToDate(dateStr);
            return d;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
