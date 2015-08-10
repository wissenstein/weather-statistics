package com.wissenstein.weatherstatistics.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.wissenstein.weatherstatistics.persistence")
public class MongoConfig extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "weatherstatistics";
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient();
    }
}
