package dev.fizlrock.Domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Event
 */
@Table(name = "events")
public class Event extends BaseEntity {

  @Column(name = "title", nullable = false)
  protected String title;

  @Column(name = "location", nullable = false)
  protected String location;

  @Column(name = "start_time", nullable = false)
  protected LocalDateTime startTime;

  @Column(name = "end_time", nullable = false)
  protected LocalDateTime endTime;

  // Name | Type | Nullable | Default
  // ------------+--------------------------------+----------+---------
  // id | integer | "NO" |
  // title | character varying(250) | "NO" |
  // location | character varying(250) | "NO" |
  // start_time | timestamp(6) without time zone | "NO" |
  // end_time | timestamp(6) without time zone | "NO" |
  // Indexes:

}
