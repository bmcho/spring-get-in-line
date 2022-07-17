package com.bm.getin.service;

import com.bm.getin.constant.ErrorCode;
import com.bm.getin.constant.EventStatus;
import com.bm.getin.dto.EventDTO;
import com.bm.getin.exception.GeneralException;
import com.bm.getin.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public List<EventDTO> getEvents(
            Long placeId,
            String eventName,
            EventStatus eventType,
            LocalDateTime eventStartDatetime,
            LocalDateTime eventEndDatetime)
    {
        try {
            return eventRepository.findEvents(
                    placeId, eventName, eventType, eventStartDatetime, eventEndDatetime);
        }
        catch(Exception e){
            throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
        }
    }

    public Optional<EventDTO> getEvent(Long eventId) {
        try{
            return eventRepository.findEvent(eventId);
        }
        catch(Exception e){
            throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
        }
    }

    public boolean createEvent(EventDTO eventDTO) {
        return eventRepository.insertEvent(eventDTO);
    }

    public boolean modifyEvent(Long eventId, EventDTO eventDTO) {
        try{
            return eventRepository.updateEvent(eventId,eventDTO);
        }
        catch(Exception e){
            throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
        }

    }

    public boolean removeEvent(Long eventId) {
        try{
            return eventRepository.deleteEvent(eventId);
        }
        catch(Exception e){
            throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
        }
    }

}
