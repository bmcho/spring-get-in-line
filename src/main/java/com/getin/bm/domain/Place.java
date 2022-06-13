package com.getin.bm.domain;

import com.getin.bm.constant.PlaceType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Place {

    private Long id;

    private PlaceType placeType;

    private String placeName;

    private String phoneNumber;

    private Integer capacity;

    private String memo;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
