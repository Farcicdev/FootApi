package farcic.dev.footApi.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "scopes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Scopes {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "scopes_seq")
    @SequenceGenerator(name = "scopes_seq", sequenceName = "scopes_seq", allocationSize = 1)
    private Long id;
    @Column(name = "name", unique = true, nullable = false, length = 50)
    private String name;

}
