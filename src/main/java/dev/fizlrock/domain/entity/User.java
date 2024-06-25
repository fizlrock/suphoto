package dev.fizlrock.domain.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User extends BaseEntity {

  public static enum Role {
    None, Client, God, Trainer
  }

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

  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @Builder.Default
  protected Set<Event> events = new HashSet<>();

  @SuppressWarnings("unused")
  private void setEvents() {
  }

}
