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
@Table(name = "districts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class District extends BaseEntity implements Serializable {
  @Column(name = "district_name", nullable = false, length = 100)
  private String districtName;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "province_id", referencedColumnName = "id")
  private Province province;

  @OneToMany(mappedBy = "district", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @Builder.Default
  List<Ward> wards = new ArrayList<>();
}
