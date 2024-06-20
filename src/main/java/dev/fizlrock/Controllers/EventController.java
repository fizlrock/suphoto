package dev.fizlrock.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.openapitools.api.EventsApi;
import org.openapitools.model.EventDTO;
import org.openapitools.model.ID;

@RestController("/events")
public class EventController implements EventsApi {

  // Base CRUD

  @Override
  public ResponseEntity<List<EventDTO>> getAllEvents(Integer page_size, Integer page) {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  @Override
  public ResponseEntity<EventDTO> createEvent(EventDTO eventDTO) {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  @Override
  public ResponseEntity<Void> deleteEventById(Long eventID) {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  @Override
  public ResponseEntity<EventDTO> findEventById(Long eventID) {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  @Override
  public ResponseEntity<EventDTO> updateEvent(Long eventID, EventDTO eventDTO) {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  // Logic

  
  @Override
  public ResponseEntity<Void> addTrainerToEvent(Long eventID, ID ID) {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  @Override
  public ResponseEntity<List<ID>> getAllTrainersOfEvent(Long eventID) {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  @Override
  public ResponseEntity<Void> removeTrainerFromEvent(Long eventID, ID ID) {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

}
