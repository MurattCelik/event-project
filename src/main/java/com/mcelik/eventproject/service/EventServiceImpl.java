package com.mcelik.eventproject.service;

import static com.mcelik.eventproject.constant.EventConstant.LUNCH_TIME;
import static com.mcelik.eventproject.constant.EventConstant.MINUTE_FORTY_FOUR;
import static com.mcelik.eventproject.constant.EventConstant.TIME_ELEVEN;
import static com.mcelik.eventproject.constant.EventConstant.TIME_FORMAT;
import static com.mcelik.eventproject.constant.EventConstant.TIME_FORMAT_TIME_HOUR;
import static com.mcelik.eventproject.constant.EventConstant.TIME_FORMAT_TIME_MINUTE;
import com.mcelik.eventproject.dto.EventDto;
import static com.mcelik.eventproject.enumEvent.BreakTime.LUNCH;
import static com.mcelik.eventproject.enumEvent.BreakTime.NETWORKING_EVENT;
import com.mcelik.eventproject.model.Event;
import com.mcelik.eventproject.model.Presentation;
import com.mcelik.eventproject.model.PresentationResponse;
import com.mcelik.eventproject.repository.EventRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Slf4j
@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public Event saveEvent(EventDto eventDto) {
        long startMs = System.currentTimeMillis();
        boolean result = false;
        Event savedEvent = null;
        try {
            Event event = convertSaveEntity(eventDto);
            savedEvent = eventRepository.save(event);
            result = true;
        } catch (Exception ex) {
            log.error("[saveEvent()] saved failed :: eventName:{}, duration:{}", eventDto.getEventName(),
                    (System.currentTimeMillis() - startMs), ex);
        }
        log.info("[saveEvent()] saved event :: eventName:{}, result:{}, duration:{}",
                savedEvent.getEventName(), result, (System.currentTimeMillis() - startMs));
        return savedEvent;
    }

    @Override
    public Event findById(Integer id) {
        long startMs = System.currentTimeMillis();
        boolean result = false;
        Event event = null;
        try {
            Optional<Event> eventOptional = eventRepository.findById(id);
            if (eventOptional.isPresent()){
                event = eventOptional.get();
                result = true;
            }
        } catch (Exception ex) {
            log.error("[findById()] get event failed :: id:{}, duration:{}",
                    id, (System.currentTimeMillis() - startMs), ex);
        }
        log.info("[findById()] get event :: eventId:{}, result:{}, duration:{}",
                id, result, (System.currentTimeMillis() - startMs));
        return event;
    }

    @Override
    public Event updateEvent(Integer id, EventDto eventDto) {
        long startMs = System.currentTimeMillis();
        boolean result = false;
        Event updateEvent = null;
        try {
            if (Objects.nonNull(id)) {
                Optional<Event> eventOptional = eventRepository.findById(id);
                if (eventOptional.isPresent()) {
                    Event event = eventOptional.get();
                    Event convertUpdateEvent = convertUpdateEntity(event, eventDto);
                    updateEvent = eventRepository.save(convertUpdateEvent);
                    result = true;
                }
            }
        } catch (Exception ex) {
            log.error("[updateEvent()] update failed :: eventName:{}, duration:{}", eventDto.getEventName(),
                    (System.currentTimeMillis() - startMs), ex);
        }
        log.info("[updateEvent()] update event :: eventName:{}, result:{}, duration:{}",
                updateEvent.getEventName(), result, (System.currentTimeMillis() - startMs));
        return updateEvent;
    }

    @Override
    public List<Event> findAll() {
        long startMs = System.currentTimeMillis();
        boolean result = false;
        List<Event> eventList = new ArrayList<>();
        try {
            eventList = eventRepository.findAll();
            result = true;
        } catch (Exception ex) {
            log.error("[findAll()] findAll data :: duration:{}", (System.currentTimeMillis() - startMs), ex);
        }
        log.info("[findAll()] findAll data :: size:{}, result:{}, duration:{}",
                eventList.size(), result, (System.currentTimeMillis() - startMs));
        return eventList;
    }

    @Override
    public void deleteEvent(Integer id) {
        long startMs = System.currentTimeMillis();
        boolean result = false;
        try {
            eventRepository.deleteById(id);
            result = true;
        } catch (Exception ex) {
            log.error("[deleteEvent()] delete failed :: id:{}, duration:{}",
                    id, (System.currentTimeMillis() - startMs), ex);
        }
        log.info("[deleteEvent()] delete data :: id:{}, result:{}, duration:{}",
                id, result, (System.currentTimeMillis() - startMs));
    }

    @Override
    public List<PresentationResponse> getPresentationProgram(int numberOfHalls) {
        long startMs = System.currentTimeMillis();
        boolean result = false;
        List<PresentationResponse> response = new ArrayList<>();
        try {
            List<Event> events = eventRepository.findAll();
            if (!CollectionUtils.isEmpty(events)) {
                int numberHall = 1;
                while (numberOfHalls != 0) {
                    PresentationResponse presentationResponse = new PresentationResponse();
                    List<Presentation> presentations = generatePresentation(events);
                    if (!CollectionUtils.isEmpty(presentations)) {
                        presentationResponse.setPresentationList(presentations);
                        presentationResponse.setNumberHall(numberHall++);
                        response.add(presentationResponse);
                        numberOfHalls -= 1;
                    } else {
                        break;
                    }
                }
                result = true;
            }
        } catch (Exception ex) {
            log.error("[getPresentationProgram()]  getPresentationProgram failed :: duration:{}",
                    (System.currentTimeMillis() - startMs), ex);
        }
        log.info("[getPresentationProgram()] getPresentationProgram finished :: result:{}, duration:{}",
                result, (System.currentTimeMillis() - startMs));
        return response;
    }


    public static List<Presentation> generatePresentation(List<Event> events) {
        int startTimeHour = 9;  //start time
        int endTimeHour = 17; //end time
        int totalMinutes = (endTimeHour - startTimeHour) * 60;
        List<Presentation> presentationList = new ArrayList<>();

        for (int i = 0; i < totalMinutes; i++) {
            int currentHour =  getCurrentHour(startTimeHour, i); //start hour
            int currentMinute =  getCurrentMinute(i); //start minute

            if (CollectionUtils.isEmpty(events)) {
                break;
            }
            Random rand = new Random();
            Event event = events.get(rand.nextInt(events.size()));
            int duration = event.getDuration();
            if (i + duration <= totalMinutes) {
                int endHour =  getEndHour(startTimeHour, duration, i); //end hour
                int endMinute = getEndMinute(duration, i); // end minute

                ///LUNCH TIME///
                if (checkLunchTime(currentHour, currentMinute)) {
                    Presentation presentation = prepareLunch(currentHour, currentMinute, startTimeHour, i);
                    i += LUNCH.duration - 1;
                    presentationList.add(presentation);
                    continue;
                } else if (checkLunch(currentHour, currentMinute, endHour, endMinute)) {
                    i -= 1;
                    continue;
                }

                Presentation presentation = preparePresentation(currentHour, currentMinute, endHour, endMinute, event);
                presentationList.add(presentation);
                events.remove(event);
            } else {
                ///NETWORKING EVENT TIME///
                Presentation presentation = prepareNetworkingEvent(currentHour, currentMinute, i);
                presentationList.add(presentation);
                break;
            }
            i += duration - 1;
        }
        return presentationList;
    }

    private static boolean checkLunchTime(int currentHour, int currentMinute) {
        return currentHour == LUNCH_TIME && currentMinute == 0;
    }

    private static boolean checkLunch(int currentHour, int currentMinute, int endHour, int endMinute) {
        return (currentHour == LUNCH_TIME && currentMinute != 0)
                || (endHour == LUNCH_TIME && endMinute != 0)
                || (currentHour == TIME_ELEVEN && currentMinute > MINUTE_FORTY_FOUR)
                || (endHour == TIME_ELEVEN && endMinute > MINUTE_FORTY_FOUR);
    }

    private static Presentation prepareNetworkingEvent(int currentHour, int currentMinute, int i) {
        return Presentation.builder()
                        .presentationTime(String.format(TIME_FORMAT, currentHour, currentMinute,
                                TIME_FORMAT_TIME_HOUR, TIME_FORMAT_TIME_MINUTE))
                        .eventName(NETWORKING_EVENT.value)
                        .build();
    }

    private static Presentation prepareLunch(int currentHour, int currentMinute, int startTimeHour, int i) {
        int endHour = getEndHour(startTimeHour, LUNCH.duration, i);
        int endMinute = getEndMinute(LUNCH.duration, i);
        Presentation lunchPresentation =
                Presentation.builder()
                        .presentationTime(String.format(TIME_FORMAT, currentHour, currentMinute, endHour, endMinute))
                        .eventName(LUNCH.value)
                        .duration(LUNCH.duration)
                        .durationUnit(LUNCH.durationUnit).build();
        return lunchPresentation;
    }

    private static int getEndHour(int startTimeHour, int duration, int i) {
        return startTimeHour + (i + duration) / 60;
    }

    private static int getEndMinute(int duration, int i) {
        return (i + duration) % 60;
    }

    private static int getCurrentMinute(int i) {
        return  i % 60;
    }

    private static int getCurrentHour(int startTimeHour, int i) {
        return startTimeHour + i / 60;
    }

    private static Presentation preparePresentation(int currentHour, int currentMinute, int endHour, int endMinute, Event event) {
        return Presentation.builder()
                .presentationTime(String.format(TIME_FORMAT, currentHour, currentMinute, endHour, endMinute))
                .eventName(event.getEventName()).speakorFullName(event.getSpeakorFullName())
                .duration(event.getDuration()).durationUnit(event.getDurationUnit()).build();
    }

    private Event convertSaveEntity(EventDto eventDto) {
        return Event.builder().eventName(eventDto.getEventName()).speakorFullName(eventDto.getSpeakorFullName())
                .duration(eventDto.getDuration()).durationUnit(eventDto.getDurationUnit()).build();
    }

    private Event convertUpdateEntity(Event event, EventDto eventDto) {
        if (Objects.nonNull(eventDto.getEventName())) {
            event.setEventName(eventDto.getEventName());
        }
        if (Objects.nonNull(eventDto.getSpeakorFullName())) {
            event.setSpeakorFullName(eventDto.getSpeakorFullName());
        }
        if (Objects.nonNull(eventDto.getDuration())) {
            event.setDuration(eventDto.getDuration());
        }
        if (Objects.nonNull(eventDto.getDurationUnit())) {
            event.setDurationUnit(eventDto.getDurationUnit());
        }
        return event;
    }

}
