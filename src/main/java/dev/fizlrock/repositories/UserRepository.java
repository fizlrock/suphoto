package dev.fizlrock.repositories;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import dev.fizlrock.domain.entity.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

  public Optional<User> findByUsername(String username);

}
