package farcic.dev.footApi.infra.repository;

import farcic.dev.footApi.infra.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {
}
