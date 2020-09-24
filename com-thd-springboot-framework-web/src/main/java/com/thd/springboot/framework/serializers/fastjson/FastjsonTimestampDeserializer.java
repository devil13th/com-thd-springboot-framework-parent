//package com.thd.springboot.framework.serializers.fastjson;
//
//import com.alibaba.fastjson.parser.DefaultJSONParser;
//import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
//import com.thd.springboot.framework.utils.DateUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.lang.reflect.Type;
//import java.sql.Timestamp;
//import java.util.Date;
//
//
//public class FastjsonTimestampDeserializer implements ObjectDeserializer {
//
//    @Override
//    public Timestamp deserialze(DefaultJSONParser defaultJSONParser, Type type, Object o) {
//
//        Logger logger = LoggerFactory.getLogger(this.getClass());
//
//        logger.info(" fastjson 时间戳反序列化:" + defaultJSONParser.getLexer().stringVal());
//
//        String timestampStr = defaultJSONParser.getLexer().stringVal();
//        return DateUtils.stringToTimestamp(timestampStr);
//    }
//
//    @Override
//    public int getFastMatchToken() {
//        return 0;
//    }
//}
