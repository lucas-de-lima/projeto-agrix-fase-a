package com.betrybe.agrix.util;

import com.betrybe.agrix.controller.dto.CropBodyDto;
import com.betrybe.agrix.controller.dto.CropDto;
import com.betrybe.agrix.controller.dto.FarmBodyDto;
import com.betrybe.agrix.controller.dto.FarmDto;
import com.betrybe.agrix.models.entities.Crops;
import com.betrybe.agrix.models.entities.Farms;

/**
 * ModelDtoConverter.
 */

public class ModelDtoConverter {
  /**
   * Convert from dto to model.
   */
  
  public static Farms dtoToModel(FarmBodyDto dto) {
    Farms farm = new Farms();

    farm.setName(dto.name());
    farm.setSize(dto.size());
    
    return farm;
  }

  /**
   * Convert from model to dto.
   */

  public static FarmDto modelToDto(Farms farm) {
    return new FarmDto(
      farm.getId(),
      farm.getName(),
      farm.getSize()
    );
  }

  /**
   * Convert from dto to model.
   */

  public static Crops dtoToCrops(CropBodyDto dto) {
    Crops crop = new Crops();

    crop.setName(dto.name());
    crop.setPlantedArea(dto.plantedArea());

    return crop;
  }

  /**
   * Convert from model to dto.
   */

  public static CropDto cropToDto(Crops crop) {
    return new CropDto(
      crop.getId(),
      crop.getName(),
      crop.getPlantedArea(),
      crop.getFarm().getId()
    );
  }

}
