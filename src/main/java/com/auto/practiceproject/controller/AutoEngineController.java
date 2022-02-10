package com.auto.practiceproject.controller;

import com.auto.practiceproject.controller.converter.AutoEngineDTOConverter;
import com.auto.practiceproject.controller.dto.response.AutoEngineResponseDTO;
import com.auto.practiceproject.service.AutoEngineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = {"Auto Engine"})
@RestController
@Slf4j
@RequestMapping("/api/engine")
@RequiredArgsConstructor
public class AutoEngineController {

  private final AutoEngineService autoEngineService;
  private final AutoEngineDTOConverter autoEngineDTOConverter;

  @ApiOperation(value = "View list of auto engines")
  @GetMapping
  public ResponseEntity<List<AutoEngineResponseDTO>> getAllAutoEngines() {
    log.trace("Controller method called to view all auto engines");
    return new ResponseEntity<>(
        autoEngineService.findAllAutoEngine().stream()
            .map(autoEngineDTOConverter::toDTO)
            .collect(Collectors.toList()),
        HttpStatus.OK);
  }

  @ApiOperation(value = "Get auto engine by id")
  @GetMapping("/{id}")
  public ResponseEntity<AutoEngineResponseDTO> getAutoEngineById(@PathVariable Long id) {
    log.trace("Controller method called to view auto engine with id: {}", id);
    return new ResponseEntity<>(
        autoEngineDTOConverter.toDTO(autoEngineService.findAutoEngineById(id)), HttpStatus.OK);
  }
}
