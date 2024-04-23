package com.thanhnt.cinemasystem.security;

import com.thanhnt.cinemasystem.entity.User;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class SecurityUserDetails implements UserDetails {

  private Long id;
  private String email;
  private String phone;
  private String password;
  private Collection<? extends GrantedAuthority> authorities;

  public static SecurityUserDetails build(User user, List<GrantedAuthority> authorityList) {
    return SecurityUserDetails.builder()
        .id(user.getId())
        .email(user.getEmail())
        .phone(user.getPhone())
        .password(user.getPassword())
        .authorities(authorityList)
        .build();
  }

  public static SecurityUserDetails build(User user) {
    return SecurityUserDetails.builder()
        .id(user.getId())
        .email(user.getEmail())
        .phone(user.getPhone())
        .password(user.getPassword())
        .build();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
