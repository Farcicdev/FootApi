package farcic.dev.footApi.repository;

import farcic.dev.footApi.entity.Scopes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScopeRepository extends JpaRepository<Scopes, Long> {
}
