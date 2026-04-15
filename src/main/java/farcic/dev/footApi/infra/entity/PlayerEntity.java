package farcic.dev.footApi.infra.entity;

import farcic.dev.footApi.domain.core.PositionEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "player")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_seq")
    @SequenceGenerator(name = "player_seq", sequenceName = "player_seq")
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private PositionEnum position;
    @Column(name = "shirt_number")
    private int shirtNumber;
    @Column(name = "url_img")
    private String urlImg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id", nullable = false)
    private ClubEntity club;
}
