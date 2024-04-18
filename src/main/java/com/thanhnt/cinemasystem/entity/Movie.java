package com.thanhnt.cinemasystem.entity;

import com.thanhnt.cinemasystem.enums.Language;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movies")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Movie extends BaseEntity implements Serializable {
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "director", nullable = false)
    private String director;

    @Column(name = "casts", nullable = false)
    private String cast;

    @Column(name = "premiere", nullable = false)
    private Long premiere;

    @Column(name = "duration", nullable = false)
    private int duration;

    @Column(name = "language", nullable = false)
    @Enumerated(EnumType.STRING)
    private Language language;

    @Column(name = "age_rated", nullable = false)
    private int ageRated;

    @Column(name = "description")
    private String description;

    @Column(name = "trailer")
    private String trailer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    private MovieGenre genre;

    @OneToMany(mappedBy = "movieTime", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<MovieTime> movieTimes = new ArrayList<>();

}
