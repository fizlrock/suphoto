package dev.fizlrock.Domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

import lombok.Builder;

@Builder
@Entity
@Table(name = "users")
public class User extends BaseEntity {

  @Column(name = "username", nullable = false)
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

  @Builder.Default
  @ManyToMany
  protected Set<Event> events = new HashSet<>();

  public static enum Role {
    None, Client, God, Trainer
  }

}
