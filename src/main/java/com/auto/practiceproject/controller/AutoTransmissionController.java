package com.auto.practiceproject.controller;


import com.auto.practiceproject.controller.converter.AutoTransmissionDOCConverter;
import com.auto.practiceproject.controller.dto.response.AutoTransmissionResponseDTO;
import com.auto.practiceproject.service.AutoTransmissionService;
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

@Api(tags = {"Auto Transmission"})
@RestController
@Slf4j
@RequestMapping("/api/transmission")
@RequiredArgsConstructor
public class AutoTransmissionController {

    private final AutoTransmissionService autoTransmissionService;
    private final AutoTransmissionDOCConverter converter;

    @ApiOperation(value = "View list of auto transmissions")
    @GetMapping
    public ResponseEntity<List<AutoTransmissionResponseDTO>> getAllAutoTransmissions() {
        log.trace("Controller method called to view all auto transmissions");
        return new ResponseEntity<>(
                autoTransmissionService.findAllAutoTransmission().stream()
                        .map(converter::toDTO)
                        .collect(Collectors.toList())
                , HttpStatus.OK);
    }

    @ApiOperation(value = "Get auto transmission by id")
    @GetMapping("/{id}")
    public ResponseEntity<AutoTransmissionResponseDTO> getAutoTransmissionById(@PathVariable Long id) {
        log.trace("Controller method called to view auto transmission with id: {}", id);
        return new ResponseEntity<>(converter
                .toDTO(autoTransmissionService.findAutoTransmissionById(id)),
                HttpStatus.OK);
    }

}
