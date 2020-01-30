package com.backend.rcs.configuration;

import com.backend.rcs.repository.AccessRepository;
import com.backend.rcs.repository.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = {UserRepository.class, AccessRepository.class})
@Configuration
public class MongoDBConfig {

}
