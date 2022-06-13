package com.getin.bm.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdminPlaceMap {

    private Integer id;

    private Integer adminId;

    private Integer placeId;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
