package com.ffcs.portalservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luozy
 * @date 2023/7/28
 * @apiNote
 */
@Component
public class RestTemplateConfig {

    @Bean
    private RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        setJacksonConverter(restTemplate);
        return  restTemplate;
    }

    private void setJacksonConverter(RestTemplate restTemplate){
        // 获取默认的HttpMessageConverters
        List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();

        // 创建MappingJackson2HttpMessageConverter，并设置支持的媒体类型
        MappingJackson2HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter();
        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        jacksonConverter.setSupportedMediaTypes(supportedMediaTypes);

        // 将Jackson转换器插入到默认的HttpMessageConverters列表的开头
        converters.add(0, jacksonConverter);

        // 重新设置RestTemplate的HttpMessageConverters
        restTemplate.setMessageConverters(converters);
    }


}
