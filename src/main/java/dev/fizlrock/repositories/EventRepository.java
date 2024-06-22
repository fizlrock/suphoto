package dev.fizlrock.Repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import dev.fizlrock.Domain.Event;

/**
 * EventRepository
 */
public interface EventRepository extends PagingAndSortingRepository<Event, Long> {

}
