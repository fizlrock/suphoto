package dev.fizlrock.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.openapitools.model.UserDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import dev.fizlrock.domain.entity.User;
import dev.fizlrock.domain.exception.UserNotFoundException;
import dev.fizlrock.repositories.UserRepository;

@Service
public class UserCrudService {

  public UserCrudService(UserRepository userRepo, ModelMapper mapper) {
    this.userRepo = userRepo;
    this.mapper = mapper;
  }

  private UserRepository userRepo;
  private ModelMapper mapper;

  public UserDTO saveUser(UserDTO rawUser) {

    User user = mapper.map(rawUser, User.class);
    user.setPassword("123123");
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
    if (!userRepo.existsById(id))
      throw new UserNotFoundException(id);
    userRepo.deleteById(id);
  }

  public List<UserDTO> findAllUsers(PageRequest pageRequest) {

    List<UserDTO> users = userRepo.findAll(pageRequest).stream()
        .map(x -> mapper.map(x, UserDTO.class))
        .collect(Collectors.toList());

    return users;
  }

}
