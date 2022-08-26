package com.techie.user;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@ComponentScan(basePackages = "com.techie.user.**")
@EnableMongoRepositories
@Configuration

public class UserModuleConfiguration {
}
