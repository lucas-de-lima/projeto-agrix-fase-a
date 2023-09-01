package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.models.entities.Farms;


/**
 * FarmDto.
 */

public record ResponseDto<T>(Farms farm) {}
