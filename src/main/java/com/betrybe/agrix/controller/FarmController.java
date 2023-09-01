package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.FarmBodyDto;
import com.betrybe.agrix.controller.dto.FarmDto;
import com.betrybe.agrix.models.entities.Farms;
import com.betrybe.agrix.services.FarmService;
import com.betrybe.agrix.util.ModelDtoConverter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


/**
 * FarmController.
 */

@RestController
@RequestMapping(value = "/farms")
public class FarmController {
  
  private final FarmService farmService;
  
  @Autowired
  public FarmController(FarmService farmService) {
    this.farmService = farmService;
  }

  /**
   * Insert a new farm.
   */

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public FarmDto insertFarm(@RequestBody FarmBodyDto farmBodyDto) {
    Farms farm = ModelDtoConverter.dtoToModel(farmBodyDto);
    Farms response = farmService.insertFarm(farm);
    return ModelDtoConverter.modelToDto(response);
  }

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public List<FarmDto> getAllFarms() {
    List<Farms> farms = farmService.getAllFarms();
    return farms.stream().map(ModelDtoConverter::modelToDto).toList();
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public FarmDto getFarmById(@PathVariable(name = "id") long id) {
    Farms farm = farmService.getFarmsById(id);
    return ModelDtoConverter.modelToDto(farm);
  }
}
