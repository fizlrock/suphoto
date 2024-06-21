package dev.fizlrock.Controllers;

import java.util.List;

import org.openapitools.api.UsersApi;
import org.openapitools.model.ID;
import org.openapitools.model.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * UserController
 */
/**
 * UserController
 */
public class UserController implements UsersApi {

  @Override
  public ResponseEntity<UserDTO> createUser(UserDTO userDTO) {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  @Override
  public ResponseEntity<Void> deleteUserById(Long userID) {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  @Override
  public ResponseEntity<UserDTO> findUserById(Long userID) {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  @Override
  public ResponseEntity<List<ID>> getAllEventsOfUser(Long userID, Integer pageNum, Integer pageSize) {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  @Override
  public ResponseEntity<List<UserDTO>> getAllUsers(Integer pageNum, Integer pageSize) {
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

  @Override
  public ResponseEntity<UserDTO> updateUser(Long userID, UserDTO userDTO) {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }
}
