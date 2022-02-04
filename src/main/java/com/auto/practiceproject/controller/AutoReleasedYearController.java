package com.auto.practiceproject.controller;

import com.auto.practiceproject.controller.converter.AutoReleasedTearDTOConverter;
import com.auto.practiceproject.controller.dto.response.AutoReleasedYearResponseDTO;
import com.auto.practiceproject.service.AutoReleasedYearService;
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

@Api(tags = {"Auto Release Year"})
@RestController
@Slf4j
@RequestMapping("/api/releasedYear")
@RequiredArgsConstructor
public class AutoReleasedYearController {

    private final AutoReleasedYearService autoReleasedYearService;
    private final AutoReleasedTearDTOConverter converter;

    @ApiOperation(value = "Get auto released year by id")
    @GetMapping("/{id}")
    public ResponseEntity<AutoReleasedYearResponseDTO> getAutoReleasedTearById(@PathVariable Long id) {
        log.trace("Controller method called to view auto released year with id: {}", id);
        return new ResponseEntity<>(converter
                .toDTO(autoReleasedYearService.findAutoReleasedYearById(id)),
                HttpStatus.OK);
    }

}
