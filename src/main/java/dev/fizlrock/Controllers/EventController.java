package dev.fizlrock.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import dev.fizlrock.Domain.Event;
import dev.fizlrock.Repositories.EventRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.openapitools.api.EventsApi;
import org.openapitools.model.EventDTO;
import org.openapitools.model.ID;

@RestController("/events")
public class EventController implements EventsApi {

  @Autowired
  EventRepository eventRepo;

  @Autowired
  ModelMapper mapper;

  // Base CRUD

  @Override
  public ResponseEntity<List<EventDTO>> getAllEvents(Integer pageNumber, Integer pageSize) {
    var pr = PageRequest.of(pageNumber, pageSize);
    var page = eventRepo.findAll(pr);

    var listOfDTO = page.toList().stream()
        .map(x -> mapper.map(x, EventDTO.class))
        .collect(Collectors.toList());

    return ResponseEntity.ok(listOfDTO);
  }

  @Override
  public ResponseEntity<EventDTO> createEvent(EventDTO eventDTO) {
    var event = mapper.map(eventDTO, Event.class);

    event.setId(null);
    eventRepo.save(event);

    var resultEvent = mapper.map(event, EventDTO.class);
    return ResponseEntity.status(HttpStatus.CREATED).body(resultEvent);
  }

  @Override
  public ResponseEntity<Void> deleteEventById(Long eventID) {

    var event = eventRepo.findById(eventID);
    if (event.isEmpty())
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    else {
      eventRepo.delete(event.get());
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
  }

  @Override
  public ResponseEntity<EventDTO> findEventById(Long eventID) {
    var event = eventRepo.findById(eventID);
    if (event.isEmpty())
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    else {
      var dto = mapper.map(event, EventDTO.class);
      return ResponseEntity.ok(dto);
    }
  }

  @Override
  public ResponseEntity<EventDTO> updateEvent(Long eventID, EventDTO eventDTO) {

    var eventInDB = eventRepo.findById(eventID);
    if (eventInDB.isEmpty())
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    var updatedEvent = mapper.map(eventDTO, Event.class);
    updatedEvent.setId(eventID);
    eventRepo.save(updatedEvent);

    return ResponseEntity.ok(mapper.map(updatedEvent, EventDTO.class));
  }

  // Logic

  @Override
  public ResponseEntity<Void> addUserToEvent(Long eventID, ID ID) {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  @Override
  public ResponseEntity<List<ID>> getAllUsersOfEvent(Long eventID) {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  @Override
  public ResponseEntity<Void> removeUserFromEvent(Long eventID, ID ID) {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

}
