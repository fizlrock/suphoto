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

import lombok.Data;

/**
 * Event
 */
@Table(name = "events")
@Entity
@Data
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
  @JoinTable(name = "events_users", joinColumns = @JoinColumn(name = "event_id"), inverseJoinColumns = @JoinColumn(name = "staff_id"))
  protected Set<User> staff = new HashSet<>();

  /**
   * Нанять сотруника на мероприятие
   */
  public void hireAnEmployee(User u) {
    if (u == null)
      throw new NullPointerException("Ссылка на пользователя не действительна");
    if (u.getEvents().contains(this))
      throw new IllegalStateException("Сотрудник уже записан на мепроприятие");

    getStaff().add(u);
    u.getEvents().add(this);
  }
  private void setStaff() {
  };

}
