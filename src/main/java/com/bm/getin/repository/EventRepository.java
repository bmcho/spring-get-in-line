package com.bm.getin.repository;

import com.bm.getin.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends
        JpaRepository<Event, Long> {

}
