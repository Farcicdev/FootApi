package farcic.dev.footApi.infra.repository;

import farcic.dev.footApi.infra.entity.StadiumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StadiumRepository extends JpaRepository<StadiumEntity, Long> {
}
