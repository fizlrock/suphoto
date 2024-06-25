package dev.fizlrock.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.openapitools.api.EventsApi;
import org.openapitools.model.EventDTO;
import org.openapitools.model.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import dev.fizlrock.domain.entity.Event;
import dev.fizlrock.repositories.EventRepository;
import dev.fizlrock.repositories.UserRepository;
import dev.fizlrock.services.EventCrudService;

@RestController("/events")
public class EventController implements EventsApi {

  @Autowired
  EventCrudService service;

  @Autowired
  ModelMapper mapper;

  // Base CRUD

  @Override
  public ResponseEntity<List<EventDTO>> getAllEvents(Integer pageNumber,
      Integer pageSize) {
    var pr = PageRequest.of(pageNumber, pageSize);
    return ResponseEntity.ok(service.findAllEvents(pr));
  }

  @Override
  public ResponseEntity<EventDTO> createEvent(EventDTO eventDTO) {
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(service.saveEvent(eventDTO));
  }

  @Override
  public ResponseEntity<Void> deleteEventById(Long eventID) {
    service.findEventById(eventID);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @Override
  public ResponseEntity<EventDTO> findEventById(Long eventID) {
    return ResponseEntity.ok(service.findEventById(eventID));
  }

  @Override
  public ResponseEntity<EventDTO> updateEvent(Long eventID, EventDTO eventDTO) {
    eventDTO.setId(eventID);
    return ResponseEntity.ok(
        service.saveEvent(eventDTO));
  }

  // // Logic

  // @Override
  // public ResponseEntity<Void> addUserToEvent(Long eventID, ID ID) {

  // var event = eventRepo.findById(eventID).get();
  // var user = userRepo.findById(ID.getId()).get();
  // // user.addEvent(event);
  // event.hireAnEmployee(user);
  // eventRepo.save(event);

  // return ResponseEntity.ok().build();
  // }

  // @Override
  // public ResponseEntity<List<ID>> getAllUsersOfEvent(Long eventID) {

  // var event = eventRepo.findById(eventID).get();

  // var users = event.getStaff().stream()
  // .map(x -> {
  // var id = new ID();
  // id.setId(x.getId());
  // return id;
  // })
  // .collect(Collectors.toList());
  // return ResponseEntity.ok(users);
  // }

  // @Override
  // public ResponseEntity<Void> removeUserFromEvent(Long eventID, ID ID) {
  // return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  // }

}
