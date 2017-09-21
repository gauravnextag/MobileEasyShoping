package com.mob.shopping.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageRouteBuilder extends RouteBuilder {
    @Value("${ftl.path}")
    private String ftlPath;
    @Override
    public void configure() throws Exception {
        from("direct:performFreemarkerTemplatingForMessageBroker")
                .to("freemarker:file:" + ftlPath + "/MessageBroker.ftl").convertBodyTo(String.class);
    }
}
