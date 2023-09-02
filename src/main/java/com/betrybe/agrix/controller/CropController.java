package com.betrybe.agrix.controller;


import com.betrybe.agrix.controller.dto.CropBodyDto;
import com.betrybe.agrix.controller.dto.CropDto;
import com.betrybe.agrix.models.entities.Crops;
import com.betrybe.agrix.services.CropService;
import com.betrybe.agrix.util.ModelDtoConverter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


/**
 * CropController.
 */

@RestController
public class CropController {
  private final CropService cropService;

  @Autowired
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * Insert crop.
   */

  @PostMapping("/farms/{farmId}/crops")
  @ResponseStatus(HttpStatus.CREATED)
  public CropDto insertCrop(
      @RequestBody CropBodyDto cropDto,
      @PathVariable(name = "farmId") long farmId) {
    Crops crop = ModelDtoConverter.dtoToCrops(cropDto);
    Crops response = cropService.insertCrops(farmId, crop);

    return ModelDtoConverter.cropToDto(response);
  }

  /**
   * Get all crops by farm id.
   */

  @GetMapping("/farms/{farmId}/crops")
  @ResponseStatus(HttpStatus.OK)
  public List<CropDto> getAllCrops(@PathVariable(name = "farmId") long farmId) {
    List<Crops> crops = cropService.getByFarmId(farmId);
    return crops.stream().map(ModelDtoConverter::cropToDto).toList();
  }

  /**
   * Get all crops.
   */

  @GetMapping("/crops")
  @ResponseStatus(HttpStatus.OK)
  public List<CropDto> getAllCrops() {
    List<Crops> crops = cropService.getAllCrops();
    return crops.stream().map(ModelDtoConverter::cropToDto).toList();
  }

  /**
   * Get crops by id.
   */

  @GetMapping("/crops/{id}")
  @ResponseStatus(HttpStatus.OK)
  public CropDto getCropsById(@PathVariable(name = "id") long id) {
    Crops crop = cropService.getCropById(id);
    return ModelDtoConverter.cropToDto(crop);
  }

}
