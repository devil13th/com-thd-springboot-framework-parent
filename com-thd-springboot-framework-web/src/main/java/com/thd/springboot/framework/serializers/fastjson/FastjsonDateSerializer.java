package com.thd.springboot.framework.serializers.fastjson;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.thd.springboot.framework.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FastjsonDateSerializer implements ObjectSerializer {
    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType,
                      int features) throws IOException {

        Logger logger = LoggerFactory.getLogger(this.getClass());

        logger.info(" fastjson 日期序列化:" + object);
        SerializeWriter out = serializer.out;
        if (object == null) {
            out.writeNull();
            return;
        }
        out.writeLong(DateUtils.dateToLong((Date)object));
    }
}