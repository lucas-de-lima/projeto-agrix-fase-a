package com.betrybe.agrix.util;

import com.betrybe.agrix.controller.dto.FarmBodyDto;
import com.betrybe.agrix.controller.dto.FarmDto;
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

}
