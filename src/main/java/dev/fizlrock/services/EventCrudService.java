package dev.fizlrock.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.openapitools.model.EventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import dev.fizlrock.domain.entity.Event;
import dev.fizlrock.domain.exception.EventNotFoundException;
import dev.fizlrock.repositories.EventRepository;

/**
 * EventCrudService
 */
 @Service
public class EventCrudService {

  @Autowired
  EventRepository eventRepo;

  @Autowired
  ModelMapper mapper;

  public EventDTO saveEvent(EventDTO eventDTO) {
    var event = mapper.map(eventDTO, Event.class);
    var savedEvent = eventRepo.save(event);
    var savedEventDTO = mapper.map(savedEvent, EventDTO.class);
    return savedEventDTO;
  }

  public EventDTO createEvent(EventDTO eventDTO) {
    eventDTO.setId(null);
    return saveEvent(eventDTO);
  }

  public EventDTO findEventById(Long id) {
    var event = eventRepo.findById(id);
    if (event.isPresent())
      return mapper.map(event, EventDTO.class);
    else
      throw new EventNotFoundException(id);
  }

  public void deleteEventById(Long id) {
    var event = eventRepo.findById(id);
    if (event.isPresent())
      eventRepo.deleteById(id);
    else
      throw new EventNotFoundException(id);
  }

  public List<EventDTO> findAllEvents(PageRequest pageRequest) {

    List<EventDTO> users = eventRepo.findAll(pageRequest).stream()
        .map(x -> mapper.map(x, EventDTO.class))
        .collect(Collectors.toList());

    return users;
  }
}