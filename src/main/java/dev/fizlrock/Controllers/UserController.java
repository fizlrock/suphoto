package dev.fizlrock.Controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.openapitools.api.UsersApi;
import org.openapitools.model.ID;
import org.openapitools.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import dev.fizlrock.Domain.User;
import dev.fizlrock.Repositories.UserRepository;

/**
 * UserController
 */
@RestController("/users")
public class UserController implements UsersApi {

  @Autowired
  UserRepository userRepo;
  @Autowired
  ModelMapper mapper;

  // CRUD

  @Override
  public ResponseEntity<List<UserDTO>> getAllUsers(Integer pageSize, Integer pageNum) {

    var pr = PageRequest.of(pageSize, pageNum);
    var page = userRepo.findAll(pr);
    var list_of_users = page.stream()
        .map(x -> mapper.map(x, UserDTO.class))
        .collect(Collectors.toList());

    return ResponseEntity.ok(list_of_users);
  }

  public ResponseEntity<UserDTO> createUser(UserDTO userDTO) {

    var user = mapper.map(userDTO, User.class);
    user.setId(null);
    userRepo.save(user);
    var savedUserDTO = mapper.map(user, UserDTO.class);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedUserDTO);
  }

  @Override
  public ResponseEntity<Void> deleteUserById(Long userID) {
    var user = userRepo.findById(userID);

    if (user.isEmpty())
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    else {
      userRepo.deleteById(userID);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

  }

  @Override
  public ResponseEntity<UserDTO> findUserById(Long userID) {
    var user = userRepo.findById(userID);

    if (user.isEmpty())
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    else {
      var dto = mapper.map(user.get(), UserDTO.class);
      return ResponseEntity.ok(dto);
    }

  }

  @Override
  public ResponseEntity<UserDTO> updateUser(Long userID, UserDTO userDTO) {

    var user = mapper.map(userDTO, User.class);

    if (userRepo.findById(userID).isEmpty())
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    user.setId(userID);
    userRepo.save(user);

    var answerDto = mapper.map(user, UserDTO.class);

    return ResponseEntity.status(HttpStatus.ACCEPTED).body(answerDto);
  }

  // Logic

  @Override
  public ResponseEntity<List<ID>> getAllEventsOfUser(Long userID, Integer pageNum, Integer pageSize) {

    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  @Override
  public ResponseEntity<Void> inviteuserToEvent(Long userID, ID ID) {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  @Override
  public ResponseEntity<Void> kickUserFromEvent(Long userID, ID ID) {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

}
