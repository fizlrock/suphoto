package dev.fizlrock.Domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  protected Integer id;

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

  public static enum Role {
    None, Client, God, Trainer
  }

}
