package dev.fizlrock.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import dev.fizlrock.domain.entity.User;
import dev.fizlrock.repositories.UserRepository;

/**
 * UserDetailsService
 */
public class MainUserDetailsService implements UserDetailsService {

  public MainUserDetailsService(UserRepository userRepo) {
    this.userRepo = userRepo;
  }

  private UserRepository userRepo;

  @Override
  public UserDetails loadUserByUsername(String username)
      throws UsernameNotFoundException {

    Optional<User> user = userRepo.findByUsername(username);

    var userDetails = org.springframework.security.core.userdetails.User.builder()
        .username(username)
        .build();

    throw new UnsupportedOperationException("Unimplemented method 'loadUserByUsername'");
  }

}
