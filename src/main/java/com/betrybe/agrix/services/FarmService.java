package com.betrybe.agrix.services;

import com.betrybe.agrix.exeptions.FarmNotFoundExeption;
import com.betrybe.agrix.models.entities.Farms;
import com.betrybe.agrix.models.repositories.FarmsRepositories;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * FarmService.
 */

@Service
public class FarmService {
  private FarmsRepositories farmsRepositories;
  
  @Autowired
  public FarmService(FarmsRepositories farmsRepositories) {
    this.farmsRepositories = farmsRepositories;
  }


  public Farms insertFarm(Farms farm) {
    return farmsRepositories.save(farm);
  }

  public List<Farms> getAllFarms() {
    return farmsRepositories.findAll();
  }

  /**
   * Get farm by id.
   */

  public Farms getFarmsById(Long id) {
    Optional<Farms> farm = farmsRepositories.findById(id);
    if (farm.isEmpty()) {
      throw new FarmNotFoundExeption();
    }
    return farm.get();
  }

}
