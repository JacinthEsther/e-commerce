package com.techie.user;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@ComponentScan(basePackages = "com.techie.user.*")
public class ModuleConfigTest {
}
