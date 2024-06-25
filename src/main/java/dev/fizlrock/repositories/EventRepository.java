package dev.fizlrock.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import dev.fizlrock.domain.entity.Event;


/**
 * EventRepository
 */
public interface EventRepository extends PagingAndSortingRepository<Event, Long> {

}
