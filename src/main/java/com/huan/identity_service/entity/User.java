package com.huan.identity_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Size(min = 4, message = "USERNAME_INVALID")
  private String username;

  @Size(min = 8, message = "PASSWORD_INVALID")
  private String password;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "lastName")
  private String lastName;

  private String email;
  private Date dob;

  private Set<String> roles;
}
