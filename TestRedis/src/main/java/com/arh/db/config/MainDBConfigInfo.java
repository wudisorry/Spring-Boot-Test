package com.arh.db.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("datasource.main")
public class MainDBConfigInfo extends DBConfigInfo{

}
