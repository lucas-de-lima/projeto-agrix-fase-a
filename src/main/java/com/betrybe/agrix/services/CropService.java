package com.betrybe.agrix.services;

import com.betrybe.agrix.exeptions.CropNotFoundExeption;
import com.betrybe.agrix.exeptions.FarmNotFoundExeption;
import com.betrybe.agrix.models.entities.Crops;
import com.betrybe.agrix.models.entities.Farms;
import com.betrybe.agrix.models.repositories.CropsRepositories;
import com.betrybe.agrix.models.repositories.FarmsRepositories;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * FarmService.
 */

@Service
public class CropService {
  private FarmsRepositories farmsRepositories;
  private CropsRepositories cropsRepositories;
  
  @Autowired
  public CropService(FarmsRepositories farmsRepositories, CropsRepositories cropsRepositories) {
    this.farmsRepositories = farmsRepositories;
    this.cropsRepositories = cropsRepositories;
  }

  /**
   * Insert crops.
   */

  public Crops insertCrops(Long farmId, Crops crop) {
    Optional<Farms> farm = farmsRepositories.findById(farmId);
    if (farm.isEmpty()) {
      throw new FarmNotFoundExeption();
    }
    crop.setFarm(farm.get());
    return cropsRepositories.save(crop);
  }

  /**
   * Get crops by farm id.
   */

  public List<Crops> getByFarmId(Long farmId) {
    Optional<Farms> farm = farmsRepositories.findById(farmId);
    if (farm.isEmpty()) {
      throw new FarmNotFoundExeption();
    }
    return farm.get().getCrops();
  }

  /**
   * Get all crops.
   */

  public List<Crops> getAllCrops() {
    return cropsRepositories.findAll();
  }

  /**
   * Get crop by id.
   */
  
  public Crops getCropById(Long id) {
    Optional<Crops> crops = cropsRepositories.findById(id);
    if (crops.isEmpty()) {
      throw new CropNotFoundExeption();
    }
    return crops.get();
  }

}
