package com.thd.springboot.framework.generator;

import com.google.common.base.CaseFormat;

/**
 * com.thd.springboot.framework.generator.CamelTester
 *
 * @author: wanglei62
 * @DATE: 2020/3/21 22:51
 **/
public class CamelTester {
    public static void main(String[] args){
            System.out.println(CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, "test-data"));//testData
            System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "test_data"));//testData
            System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "test_data"));//TestData

            System.out.println(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "testdata"));//testdata
            System.out.println(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "TestData"));//test_data
            System.out.println(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, "testData"));//test-data


    }
}
