package dev.fizlrock.services;

import org.modelmapper.ModelMapper;
import org.openapitools.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.fizlrock.domain.User;
import dev.fizlrock.repositories.UserRepository;
import javassist.NotFoundException;

/**
 * 
 * Функционал:
 * <ol>
 * <li>Создать пользователя
 * <li>Изменить пользователя по id
 * <li>Получить всех пользователей с пагинацией
 * <li>Создать мероприятие
 * <li>Изменить мероприятие по id
 * <li>Получить список мероприятий по id
 * </ol>
 */
@Service
public class SUPhoto {

  @Autowired
  private UserRepository userRepo;

  @Autowired
  private ModelMapper mapper;

  public UserDTO saveUser(UserDTO rawUser) {

    User user = mapper.map(rawUser, User.class);
    User savedUser = userRepo.save(user);
    UserDTO savedUserDTO = mapper.map(savedUser, UserDTO.class);
    return savedUserDTO;
  }

  public UserDTO createUser(UserDTO rawUser) {
    rawUser.setId(null);

    return saveUser(rawUser);
  }

  public UserDTO findUserById(Long id) throws NotFoundException {
    var optionalUser = userRepo.findById(id);

    if (optionalUser.isPresent())
      return mapper.map(optionalUser, UserDTO.class);
    else
      throw new NotFoundException("Пользователь не найден");
  }

}
