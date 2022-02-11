package com.auto.practiceproject.controller;

import com.auto.practiceproject.controller.converter.AutoBrandDTOConverter;
import com.auto.practiceproject.controller.dto.response.AutoBrandResponseDTO;
import com.auto.practiceproject.service.AutoBrandService;
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

@Api(tags = {"Auto Brand"})
@RestController
@Slf4j
@RequestMapping("/api/brand")
@RequiredArgsConstructor
public class AutoBrandController {

  private final AutoBrandService autoBrandService;
  private final AutoBrandDTOConverter autoBrandDTOConverter;

  @ApiOperation(value = "View list of auto brand")
  @GetMapping
  public ResponseEntity<List<AutoBrandResponseDTO>> getAllAutoBrands() {
    log.trace("Controller method called to view all autoBrands");
    return new ResponseEntity<>(
        autoBrandService.findAll().stream()
            .map(autoBrandDTOConverter::toDTO)
            .collect(Collectors.toList()),
        HttpStatus.OK);
  }

  @ApiOperation(value = "Get auto brand by id")
  @GetMapping("/{id}")
  public ResponseEntity<AutoBrandResponseDTO> getAutoBrandById(@PathVariable Long id) {
    log.trace("Controller method called to view auto brand with id: {}", id);
    return new ResponseEntity<>(
        autoBrandDTOConverter.toDTO(autoBrandService.findAutoBrandById(id)), HttpStatus.OK);
  }
}
