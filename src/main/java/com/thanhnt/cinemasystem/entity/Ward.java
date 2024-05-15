package com.thanhnt.cinemasystem.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "wards")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Ward extends BaseEntity implements Serializable {
  @Column(name = "ward_name", nullable = false, length = 100)
  private String wardName;

  @Column(name = "ward_code")
  private Long wardCode;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "district_id", referencedColumnName = "id")
  private District district;
}
