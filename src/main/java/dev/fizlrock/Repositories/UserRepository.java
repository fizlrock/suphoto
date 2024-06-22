package dev.fizlrock.Repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import dev.fizlrock.Domain.User;

/**
 * UserRepository
 */
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

}
