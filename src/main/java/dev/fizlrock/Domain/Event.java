package dev.fizlrock.Domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Event
 */
@Table(name = "events")
@Entity
public class Event extends BaseEntity {

  @Column(name = "title", nullable = false)
  protected String title;

  @Column(name = "location", nullable = false)
  protected String location;

  @Column(name = "start_time", nullable = false)
  protected LocalDateTime startTime;

  @Column(name = "end_time", nullable = false)
  protected LocalDateTime endTime;

  @ManyToMany
  @JoinTable(name = "events_staff", joinColumns = @JoinColumn(name = "event_id"), inverseJoinColumns = @JoinColumn(name = "staff_id"))
  protected Set<User> staff = new HashSet<>();

}
