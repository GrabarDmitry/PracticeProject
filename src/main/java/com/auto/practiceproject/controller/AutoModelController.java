package com.auto.practiceproject.controller;

import com.auto.practiceproject.controller.converter.AutoModelDTOConverter;
import com.auto.practiceproject.controller.dto.response.AutoModelResponseDTO;
import com.auto.practiceproject.service.AutoModelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = {"Auto Model"})
@RestController
@Slf4j
@RequestMapping("/api/model")
@RequiredArgsConstructor
public class AutoModelController {

    private final AutoModelService autoModelService;
    private final AutoModelDTOConverter autoModelDTOConverter;

    @ApiOperation(value = "View list of auto models")
    @GetMapping
    public ResponseEntity<List<AutoModelResponseDTO>> getAllAutoModels(
            @RequestParam(name = "filter", required = false) String filter) {
        log.trace("Controller method called to view all Auto models");
        return new ResponseEntity<>(
                autoModelService.findAllAutoModel(filter).stream()
                        .map(autoModelDTOConverter::toDTO)
                        .collect(Collectors.toList())
                , HttpStatus.OK);
    }

    @ApiOperation(value = "Get auto model by id")
    @GetMapping("/{id}")
    public ResponseEntity<AutoModelResponseDTO> getAutoModelById(@PathVariable Long id) {
        log.trace("Controller method called to view auto model with id: {}", id);
        return new ResponseEntity<>(autoModelDTOConverter.
                toDTO(autoModelService.findAutoModelById(id)),
                HttpStatus.OK);
    }

}
