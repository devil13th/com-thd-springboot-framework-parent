package com.thd.springboot.framework.elasticsearch.configuration;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * com.thd.springboot.framework.elasticsearch.configuration.ElasticsearchConfig
 *
 * @author: wanglei62
 * @DATE: 2021/5/8 16:09
 **/
@Configuration
public class ElasticsearchConfig {
    @Value("${spring.data.elasticsearch.cluster-nodes}")
    private String hostport;
    @Bean
    public RestHighLevelClient restHighLevelClient(){
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost(hostport.split(":")[0],Integer.parseInt(hostport.split(":")[1]),"http"))
        );
        return restHighLevelClient;
    }
}
