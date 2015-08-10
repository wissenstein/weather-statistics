package com.wissenstein.weatherstatistics.persistence;

import com.wissenstein.weatherstatistics.domain.DailyStatistics;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyStatisticsRepository
extends MongoRepository<DailyStatistics, String> {

}
