package com.auto.practiceproject.controller;

import com.auto.practiceproject.controller.converter.RegionDTOConverter;
import com.auto.practiceproject.controller.dto.response.RegionResponseDTO;
import com.auto.practiceproject.service.RegionService;
import com.auto.practiceproject.util.PageableSwagger;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = {"Region"})
@RestController
@Slf4j
@RequestMapping("/api/region")
@RequiredArgsConstructor
public class RegionController {

  private final RegionService regionService;
  private final RegionDTOConverter regionDTOConverter;

  @PageableSwagger
  @ApiOperation(value = "View list of regions")
  @GetMapping
  public ResponseEntity<Page<RegionResponseDTO>> getAllRegions(
      @ApiIgnore @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.ASC)
          Pageable pageable) {
    log.trace("Controller method called to view all regions with params: {}", pageable);
    return new ResponseEntity<>(
        regionService.findAllRegion(pageable).map(regionDTOConverter::toDTO), HttpStatus.OK);
  }

  @ApiOperation(value = "Get region by id")
  @GetMapping("/{id}")
  public ResponseEntity<RegionResponseDTO> getRegionById(@PathVariable Long id) {
    log.trace("Controller method called to view region with id: {}", id);
    return new ResponseEntity<>(
        regionDTOConverter.toDTO(regionService.findRegionById(id)), HttpStatus.OK);
  }
}
