package dev.fizlrock.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.fizlrock.domain.entity.Event;
import dev.fizlrock.domain.entity.User;
import dev.fizlrock.domain.entity.User.Role;
import dev.fizlrock.domain.exception.EventNotFoundException;
import dev.fizlrock.domain.exception.UserNotFoundException;
import dev.fizlrock.repositories.EventRepository;
import dev.fizlrock.repositories.UserRepository;

/**
 * TrainerService
 */
@Service
public class TrainerService {

  @Autowired
  UserRepository userRepo;

  @Autowired
  EventRepository eventRepo;

  @Autowired
  ModelMapper mapper;

  /**
   * Добавляет пользователя в список ведущих мероприятия
   * <br>
   * Возможные ошибки:
   * <ol>
   * <li>Мероприятие не найдено
   * <li>Пользователь не найден
   * <li>Пользователь не является инструктором
   * <li>Инструктор уже записан на мероприятие
   * </ol>
   * 
   * @param trainerId
   * @param eventId
   */
  public void addEventToTrainer(Long trainerId, Long eventId) {

    var event = eventRepo
        .findById(eventId)
        .orElseThrow(() -> new EventNotFoundException(eventId));

    var user = userRepo
        .findById(trainerId)
        .orElseThrow(() -> new UserNotFoundException(trainerId));

    if (user.getRole() != Role.Trainer)
      throw new RuntimeException();
    // Тут обработать ошибку

    if (event.getStaff().contains(user))
      throw new RuntimeException();

    event.getStaff().add(user);
    user.getEvents().add(event);

  }

  /**
   * Получить все идентификаторы инструкторов, ведущих данное мероприятие
   * 
   * @return Список индентификаторов инструкторов
   */
  public List<Long> getAllTranersOfEvent(Long eventID) {
    var event = eventRepo
        .findById(eventID)
        .orElseThrow(() -> new EventNotFoundException(eventID));

    var idsOfStaff = event.getStaff().stream()
        .map(User::getId)
        .collect(Collectors.toList());

    return idsOfStaff;
  }

  /**
   * Получить все идентификаторы инструкторов, ведущих данное мероприятие
   * 
   * @param trainerID - идентификатор инструктора
   * @return Список идентификаторов мепроприятий
   */
  public List<Long> getAllEventsOfTrainer(Long trainerID) {

    var user = userRepo
        .findById(trainerID)
        .orElseThrow(() -> new UserNotFoundException(trainerID));

    var idsOfEvents = user.getEvents().stream()
        .map(Event::getId)
        .collect(Collectors.toList());

    return idsOfEvents;
  }

}
