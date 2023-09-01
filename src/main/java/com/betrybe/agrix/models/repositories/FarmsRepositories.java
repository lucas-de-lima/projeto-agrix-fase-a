package com.betrybe.agrix.models.repositories;

import com.betrybe.agrix.models.entities.Farms;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * FarmsRepositories.
 */

public interface FarmsRepositories extends JpaRepository<Farms, Long> {}
