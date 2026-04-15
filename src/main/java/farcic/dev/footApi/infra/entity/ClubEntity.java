package farcic.dev.footApi.infra.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "club")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClubEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "club_seq")
    @SequenceGenerator(name = "club_seq", sequenceName = "club_seq")
    private Long id;
    private String name;
    private LocalDate founded;
    @Column(name = "url_img")
    private String urlImg;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    //Boolean como objeto porque a migration nao esta com NOT NULL
    private Boolean active;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stadium_id", unique = true)
    private StadiumEntity stadium;

    @OneToMany(mappedBy = "club")
    private List<PlayerEntity> player;

}
