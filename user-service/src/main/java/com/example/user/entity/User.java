package com.example.user.entity;

import jakarta.persistence.*;
import java.util.UUID;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {

  @Id @GeneratedValue @UuidGenerator private UUID id;

  private String username;

  private String email;

  private String phoneNumber;

  private String password;
}
