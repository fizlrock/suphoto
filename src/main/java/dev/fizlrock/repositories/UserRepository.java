package dev.fizlrock.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import dev.fizlrock.domain.entity.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

}
