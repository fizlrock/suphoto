package dev.fizlrock.Domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User extends BaseEntity {

  @Column(name = "username", nullable = false, unique = true)
  @Length(min = 5, max = 30)
  protected String username;

  @Column(name = "firstName", nullable = false)
  @Length(min = 5, max = 30)
  protected String firstName;

  @Column(name = "lastName", nullable = false)
  @Length(min = 5, max = 30)
  protected String lastName;

  @Column(name = "patronymic", nullable = true)
  @Length(min = 5, max = 30)
  protected String partonymic;

  @Column(name = "role", nullable = false)
  @Builder.Default
  @Enumerated(EnumType.STRING)
  protected Role role = Role.None;

  // @Builder.Default
  @ManyToMany
  @JoinTable(name = "events_users", joinColumns = @JoinColumn(name = "staff_id"), inverseJoinColumns = @JoinColumn(name = "event_id"))
  protected Set<Event> events = new HashSet<>();

  public static enum Role {
    None, Client, God, Trainer
  }

}
