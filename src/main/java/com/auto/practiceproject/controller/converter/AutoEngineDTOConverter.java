package com.auto.practiceproject.controller.converter;

import com.auto.practiceproject.controller.dto.response.AutoEngineResponseDTO;
import com.auto.practiceproject.model.AutoEngine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AutoEngineDTOConverter {

  public AutoEngineResponseDTO toDTO(AutoEngine autoEngine) {
    log.trace("Convert AutoEngine: {}, to AutoEngineResponseDTO", autoEngine);
    return new AutoEngineResponseDTO(autoEngine.getId(), autoEngine.getType());
  }
}
