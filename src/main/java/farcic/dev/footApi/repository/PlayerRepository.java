package farcic.dev.footApi.repository;

import farcic.dev.footApi.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
