package com.thanhnt.cinemasystem.entity;

import com.thanhnt.cinemasystem.enums.Gender;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class User extends BaseEntity implements Serializable {

  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "gender")
  @Enumerated(EnumType.STRING)
  private Gender gender;

  @Column(name = "phone", length = 20, nullable = false, unique = true)
  private String phone;

  @Column(name = "date_of_birth", nullable = false)
  private Long dateOfBirth;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "province_id", referencedColumnName = "id")
  private Province province;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "district_id", referencedColumnName = "id")
  private District district;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ward_id", referencedColumnName = "id")
  private Ward ward;

  @Column(name = "is_first_login", nullable = false)
  @Builder.Default
  private boolean isFirstLogin = true;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(
      name = "user_role",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private List<Role> roles;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @Builder.Default
  private List<RefreshToken> refreshTokens = new ArrayList<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @Builder.Default
  private List<TicketOrder> ticketOrders = new ArrayList<>();

  public void addRole(Role role) {
    if (this.roles == null) {
      this.roles = new ArrayList<>();
    }
    this.roles.add(role);
  }
}
