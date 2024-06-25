package dev.fizlrock.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.openapitools.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import dev.fizlrock.domain.entity.User;
import dev.fizlrock.domain.exception.UserNotFoundException;
import dev.fizlrock.repositories.UserRepository;

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
public class UserCrudService {

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

  public UserDTO findUserById(Long id) throws UserNotFoundException {
    Optional<User> user = userRepo.findById(id);

    if (user.isPresent())
      return mapper.map(user, UserDTO.class);
    else
      throw new UserNotFoundException(id);
  }

  public void deleteUserById(Long id) throws UserNotFoundException {

    Optional<User> user = userRepo.findById(id);

    if (user.isPresent())
      userRepo.deleteById(id);
    else
      throw new UserNotFoundException(id);
  }

  public List<UserDTO> findAllUsers(PageRequest pageRequest) {

    List<UserDTO> users = userRepo.findAll(pageRequest).stream()
        .map(x -> mapper.map(x, UserDTO.class))
        .collect(Collectors.toList());

    return users;
  }

}
