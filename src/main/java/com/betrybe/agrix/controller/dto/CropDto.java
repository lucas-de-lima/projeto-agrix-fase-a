package com.betrybe.agrix.controller.dto;

/**
 * Crop Dto.
 */

public record CropDto(Long id, String name, Double plantedArea, Long farmId) {}
