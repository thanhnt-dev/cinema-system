package com.thanhnt.cinemasystem.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "provinces")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Province extends BaseEntity implements Serializable {
  @Column(name = "province_name", nullable = false, length = 100)
  private String provinceName;

  @Column(name = "province_code")
  private Long provinceCode;

  @OneToMany(mappedBy = "province", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @Builder.Default
  private List<District> districts = new ArrayList<>();

  @OneToMany(mappedBy = "province", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @Builder.Default
  private List<Cinema> cinemas = new ArrayList<>();
}
