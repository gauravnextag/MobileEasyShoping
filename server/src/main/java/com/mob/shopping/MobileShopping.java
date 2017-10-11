package com.mob.shopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication(scanBasePackages = "com.mob.shopping")
@EnableTransactionManagement
//@PropertySources({ @PropertySource(value = "file:${mobShop.property.file.path}", ignoreResourceNotFound = true) })
@PropertySources({ @PropertySource(value = "file:/home/mobShop/conf/application.properties", ignoreResourceNotFound = true) })
@ServletComponentScan(basePackages={"com.mob.shopping.interceptor"})
public class MobileShopping extends SpringBootServletInitializer implements WebApplicationInitializer{

    public static void main(String[] args) {
        SpringApplication.run(MobileShopping.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder builder) {
        return builder.sources(MobileShopping.class);
    }
}
