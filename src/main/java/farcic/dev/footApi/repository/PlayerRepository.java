package farcic.dev.footApi.repository;

import farcic.dev.footApi.entity.Player;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    @EntityGraph(attributePaths = "club")
    List<Player> findByClubId(Long Id);

}
