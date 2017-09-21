package com.mob.shopping;


//import org.springframework.boot.web.client.RestTemplateBuilder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mob.shopping.exception.BusinessException;
import com.mob.shopping.service.MasterConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestTemplateConfiguration {

    @Autowired
    MasterConfigService masterConfigService;

    @Bean(name = "restTemplate")
    public RestTemplate restTemplateCheckbalance() throws BusinessException {

        String readTimeout = "15000";
        String connectionTimeout = "30000";

        SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        simpleClientHttpRequestFactory.setReadTimeout(Integer.parseInt(readTimeout));
        simpleClientHttpRequestFactory.setConnectTimeout(Integer.parseInt(connectionTimeout));
        RestTemplate restTemplate = new RestTemplate();
        System.clearProperty("http.proxyHost");
        System.clearProperty("http.proxyPort");
        System.clearProperty("http.proxyUser");
        System.clearProperty("http.proxyPassword");
        restTemplate.setRequestFactory(simpleClientHttpRequestFactory);
        List<MediaType> mediaTypeList = new ArrayList<>();
        mediaTypeList.add(MediaType.APPLICATION_OCTET_STREAM);
        mediaTypeList.add(MediaType.APPLICATION_JSON);
        mediaTypeList.add(MediaType.APPLICATION_XML);

        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        jsonConverter.setObjectMapper(objectMapper());
        jsonConverter.setSupportedMediaTypes(mediaTypeList);

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(new ByteArrayHttpMessageConverter());
        StringHttpMessageConverter httpMsgConverter = new StringHttpMessageConverter();
        httpMsgConverter.setWriteAcceptCharset(false);
        messageConverters.add(httpMsgConverter);
        messageConverters.add(new StringHttpMessageConverter());
        messageConverters.add(new ResourceHttpMessageConverter());
        messageConverters.add(new AllEncompassingFormHttpMessageConverter());
        messageConverters.add(new Jaxb2RootElementHttpMessageConverter());
        messageConverters.add(jsonConverter);
        restTemplate.setMessageConverters(messageConverters);
        return restTemplate;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        return objectMapper;
    }


}
