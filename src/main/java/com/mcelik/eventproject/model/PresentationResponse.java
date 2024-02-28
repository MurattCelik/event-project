package com.mcelik.eventproject.model;


import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PresentationResponse {
    Integer numberHall;
    List<Presentation> presentationList;
}
