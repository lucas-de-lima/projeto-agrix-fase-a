package com.betrybe.agrix.advices;

import com.betrybe.agrix.exeptions.FarmNotFoundExeption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * ControllerAdvice.
 */

@ControllerAdvice
public class ControllerAdviceEx {
  
  @ExceptionHandler(FarmNotFoundExeption.class)
  public ResponseEntity<String> handleInvalidCoordinateException() {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fazenda não encontrada!");
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleException() {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno!");
  }
}
