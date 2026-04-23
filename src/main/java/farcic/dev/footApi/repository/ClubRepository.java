package farcic.dev.footApi.repository;

import farcic.dev.footApi.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club, Long> {
}
