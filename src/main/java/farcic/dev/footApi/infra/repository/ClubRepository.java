package farcic.dev.footApi.infra.repository;

import farcic.dev.footApi.infra.entity.ClubEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<ClubEntity, Long> {
}
