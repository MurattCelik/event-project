package com.mcelik.eventproject.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Presentation {

    private String presentationTime;
    private String eventName;
    private String speakorFullName;
    private Integer duration;
    private String durationUnit;
}
