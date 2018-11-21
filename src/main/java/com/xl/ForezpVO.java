package com.xl;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties("com.forezp")
//@PropertySource(value = "classpath:test.properties")
@Component
public class ForezpVO {
    private String name;
    private int age;
}
