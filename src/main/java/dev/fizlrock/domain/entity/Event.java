package dev.fizlrock.domain.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Event
 */
@Table(name = "events")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Event extends BaseEntity {

  @Column(name = "title", nullable = false)
  protected String title;

  @Column(name = "location", nullable = false)
  protected String location;

  @Column(name = "start_time", nullable = false)
  protected LocalDateTime startTime;

  @Column(name = "end_time", nullable = false)
  protected LocalDateTime endTime;

  @ManyToMany(mappedBy = "events", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  protected Set<User> staff = new HashSet<>();

  @SuppressWarnings("unused")
  private void setStaff() {
  };

}
