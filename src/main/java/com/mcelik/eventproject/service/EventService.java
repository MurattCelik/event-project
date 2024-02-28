package com.mcelik.eventproject.service;

import com.mcelik.eventproject.dto.EventDto;
import com.mcelik.eventproject.model.Event;
import com.mcelik.eventproject.model.PresentationResponse;
import java.util.List;

public interface EventService {

    Event saveEvent(EventDto eventDto);
    Event findById(Integer id);
    Event updateEvent(Integer id, EventDto eventDto);
    List<Event> findAll();
    void deleteEvent(Integer id);
    List<PresentationResponse> getPresentationProgram(int numberOfHalls);
}
