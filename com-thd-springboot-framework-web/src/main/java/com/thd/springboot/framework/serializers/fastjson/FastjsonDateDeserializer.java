//package com.thd.springboot.framework.serializers.fastjson;
//
//import com.alibaba.fastjson.parser.DefaultJSONParser;
//import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
//import com.thd.springboot.framework.utils.DateUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.lang.reflect.Type;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//
//public class FastjsonDateDeserializer implements ObjectDeserializer {
//
//    @Override
//    public Date deserialze(DefaultJSONParser defaultJSONParser, Type type, Object o) {
//
//        Logger logger = LoggerFactory.getLogger(this.getClass());
//
//        logger.info(" fastjson 日期反序列化:" + defaultJSONParser.getLexer().stringVal());
//
//        String dateStr = defaultJSONParser.getLexer().stringVal();
//        return DateUtils.stringToDate(dateStr);
//
//    }
//
//    @Override
//    public int getFastMatchToken() {
//        return 0;
//    }
//}
