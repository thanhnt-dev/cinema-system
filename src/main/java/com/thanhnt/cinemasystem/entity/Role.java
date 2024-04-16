package com.thanhnt.cinemasystem.entity;

import com.thanhnt.cinemasystem.enums.RoleUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Role extends BaseEntity implements Serializable {
    @Column(name = "role_name", nullable = false, length = 100)
    @Enumerated(EnumType.STRING)
    private RoleUser role;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<UserRole> userRoles = new ArrayList<>();
}
