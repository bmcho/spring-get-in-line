package com.getin.bm.domain;

import com.getin.bm.constant.EventStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Event {

    private Long id;

    private Long placeId;

    private String eventName;

    private EventStatus eventType;

    private LocalDateTime eventStartDatetime;
    private LocalDateTime eventEndDatetime;

    private Integer currentNumberOfPeople;

    private Integer capacity;

    private String memo;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
