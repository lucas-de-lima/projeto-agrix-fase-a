package com.betrybe.agrix.models.repositories;

import com.betrybe.agrix.models.entities.Crops;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CropsRepositories.
 */

public interface CropsRepositories extends JpaRepository<Crops, Long> {}
