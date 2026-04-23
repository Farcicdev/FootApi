package farcic.dev.footApi.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stadium")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stadium {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stadium_seq")
    @SequenceGenerator(name = "stadium_seq", sequenceName = "stadium_seq", allocationSize = 1)
    private Long id;
    private String name;
    private String city;
    private Integer capacity;

    @Column(name = "url_img")
    private String urlImg;
}
