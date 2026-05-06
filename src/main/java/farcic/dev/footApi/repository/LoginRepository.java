package farcic.dev.footApi.repository;

import farcic.dev.footApi.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByEmail(String email);
}
