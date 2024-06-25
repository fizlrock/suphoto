package dev.fizlrock.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.openapitools.api.UsersApi;
import org.openapitools.model.ID;
import org.openapitools.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import dev.fizlrock.services.UserCrudService;

/**
 * UserController
 */
@RestController("/users")
public class UserController implements UsersApi {

  @Autowired
  ModelMapper mapper;

  @Autowired
  UserCrudService userService;

  // CRUD

  @Override
  public ResponseEntity<List<UserDTO>> getAllUsers(Integer pageSize, Integer pageNum) {

    var pr = PageRequest.of(pageSize, pageNum);
    var users = userService.findAllUsers(pr);
    return ResponseEntity.ok(users);
  }

  public ResponseEntity<UserDTO> createUser(UserDTO userDTO) {
    UserDTO saved_user = userService.createUser(userDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(saved_user);
  }

  @Override
  public ResponseEntity<Void> deleteUserById(Long userID) {
    userService.deleteUserById(userID);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @Override
  public ResponseEntity<UserDTO> findUserById(Long userID) {
    return ResponseEntity.ok(userService.findUserById(userID));

  }

  @Override
  public ResponseEntity<UserDTO> updateUser(Long userID, UserDTO userDTO) {
    userDTO.setId(userID);
    return ResponseEntity
        .status(HttpStatus.ACCEPTED)
        .body(userService.saveUser(userDTO));
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
