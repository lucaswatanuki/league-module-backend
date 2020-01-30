package com.rcs.back.rcs.configuration;

import com.rcs.back.rcs.repository.AccessRepository;
import com.rcs.back.rcs.repository.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = {UserRepository.class, AccessRepository.class})
@Configuration
public class MongoDBConfig {

}
