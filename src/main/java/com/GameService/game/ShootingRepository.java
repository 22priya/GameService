package com.GameService.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShootingRepository extends JpaRepository<Shooting,Integer> {
}
