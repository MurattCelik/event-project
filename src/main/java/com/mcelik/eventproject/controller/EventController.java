package com.mcelik.eventproject.controller;


import static com.mcelik.eventproject.constant.EventConstant.DELETE;
import static com.mcelik.eventproject.constant.EventConstant.DELETE_DESC;
import static com.mcelik.eventproject.constant.EventConstant.DELETE_VALUE;
import static com.mcelik.eventproject.constant.EventConstant.FIND_ALL;
import static com.mcelik.eventproject.constant.EventConstant.FIND_ALL_DESC;
import static com.mcelik.eventproject.constant.EventConstant.FIND_ALL_VALUE;
import static com.mcelik.eventproject.constant.EventConstant.GET;
import static com.mcelik.eventproject.constant.EventConstant.GET_DESC;
import static com.mcelik.eventproject.constant.EventConstant.GET_PRESENTATION_PROGRAM;
import static com.mcelik.eventproject.constant.EventConstant.GET_PRESENTATION_PROGRAM_DESC;
import static com.mcelik.eventproject.constant.EventConstant.GET_PRESENTATION_PROGRAM_VALUE;
import static com.mcelik.eventproject.constant.EventConstant.GET_VALUE;
import static com.mcelik.eventproject.constant.EventConstant.ID_VALUE;
import static com.mcelik.eventproject.constant.EventConstant.PREFIX_EVENT;
import static com.mcelik.eventproject.constant.EventConstant.SAVE;
import static com.mcelik.eventproject.constant.EventConstant.SAVE_DESC;
import static com.mcelik.eventproject.constant.EventConstant.TAG;
import static com.mcelik.eventproject.constant.EventConstant.UPDATE;
import static com.mcelik.eventproject.constant.EventConstant.UPDATE_DESC;
import static com.mcelik.eventproject.constant.EventConstant.UPDATE_VALUE;
import com.mcelik.eventproject.dto.EventDto;
import com.mcelik.eventproject.model.Event;
import com.mcelik.eventproject.model.PresentationResponse;
import com.mcelik.eventproject.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(PREFIX_EVENT)
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @Operation(summary = SAVE, description = SAVE_DESC, tags = TAG)
    @PostMapping(value = SAVE, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public Event saveEvent(@RequestBody EventDto eventDto){
        return eventService.saveEvent(eventDto);
    }

    @Operation(summary = GET, description = GET_DESC, tags = TAG)
    @GetMapping(value =GET_VALUE + ID_VALUE , produces = APPLICATION_JSON_VALUE)
    public Event findByIdEvent(@PathVariable("id") Integer id){
        return eventService.findById(id);
    }

    @Operation(summary = UPDATE, description = UPDATE_DESC , tags = TAG)
    @PostMapping(value = UPDATE_VALUE + ID_VALUE, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public Event updateEvent(@PathVariable("id") Integer id, @RequestBody EventDto eventDto){
        return eventService.updateEvent(id, eventDto);
    }

    @Operation(summary = FIND_ALL, description = FIND_ALL_DESC , tags = TAG)
    @GetMapping(value = FIND_ALL_VALUE , produces = APPLICATION_JSON_VALUE)
    public List<Event> findAll(){
        return eventService.findAll();
    }

    @Operation(summary = DELETE, description = DELETE_DESC, tags = TAG)
    @PostMapping(value = DELETE_VALUE + ID_VALUE, produces = APPLICATION_JSON_VALUE)
    public void findAll(@PathVariable("id") Integer id){
        eventService.deleteEvent(id);
    }

    @Operation(summary = GET_PRESENTATION_PROGRAM, description = GET_PRESENTATION_PROGRAM_DESC, tags = TAG)
    @PostMapping(value = GET_PRESENTATION_PROGRAM_VALUE, produces = APPLICATION_JSON_VALUE)
    public List<PresentationResponse> getPresentationProgram(
            @RequestParam(value = "numberOfHalls", defaultValue = "1", required = true) int numberOfHalls) {
       return eventService.getPresentationProgram(numberOfHalls);
    }
}
