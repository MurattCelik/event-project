package com.mcelik.eventproject.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventDto {
    private String eventName;
    private String speakorFullName;
    private Integer duration;
    private String durationUnit;
}
