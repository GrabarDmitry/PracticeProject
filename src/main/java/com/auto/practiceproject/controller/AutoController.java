package com.auto.practiceproject.controller;

import com.auto.practiceproject.controller.converter.AutoDTOConverter;
import com.auto.practiceproject.controller.dto.response.AutoResponseDTO;
import com.auto.practiceproject.service.AutoService;
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

@Api(tags = {"Auto"})
@RestController
@Slf4j
@RequestMapping("/api/auto")
@RequiredArgsConstructor
public class AutoController {

    private final AutoDTOConverter autoDTOConverter;
    private final AutoService autoService;

    @ApiOperation(value = "Get auto by id")
    @GetMapping("/{id}")
    public ResponseEntity<AutoResponseDTO> getAutoById(@PathVariable Long id) {
        log.trace("Controller method called to view auto with id: {}", id);
        return new ResponseEntity<>(autoDTOConverter.
                toDTO(autoService.findAutoById(id)),
                HttpStatus.OK);
    }

}
