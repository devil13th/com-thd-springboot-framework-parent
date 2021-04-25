package com.thd.springboot.framework.web.jsondeserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;

/**
 * com.thd.springboot.framework.jackson.jsondeserializers.StringXssDeserializer
 *
 * @author: wanglei62
 * @DATE: 2021/4/25 11:13
 **/
public class StringXssDeserializer extends JsonDeserializer<String> {




    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return der(jsonParser,deserializationContext,null);
    }

    @Override
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        return der(jsonParser,ctxt,typeDeserializer);
    }

    private String der(JsonParser jsonParser, DeserializationContext context, TypeDeserializer typeDeserializer) throws IOException {
        String str = jsonParser.getText();
        return HtmlUtils.htmlEscape(str.trim());
    }


    @Override
    public Class<?> handledType() {
        return String.class;
    }
}
