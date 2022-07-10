package com.bm.getin.config;

import com.bm.getin.repository.EventRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {
    @Bean
    public EventRepository eventRepository() {
        return new EventRepository() {
        };
    }
}
