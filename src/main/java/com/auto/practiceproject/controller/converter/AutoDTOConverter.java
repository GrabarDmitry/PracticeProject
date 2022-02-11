package com.auto.practiceproject.controller.converter;

import com.auto.practiceproject.controller.dto.response.AutoResponseDTO;
import com.auto.practiceproject.model.Auto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AutoDTOConverter {

  public AutoResponseDTO toDTO(Auto auto) {
    log.trace("Convert Auto: {}, to AutoResponseDTO", auto);
    return new AutoResponseDTO(
        auto.getId(),
        auto.getMileage(),
        auto.getEngineCapacity(),
        auto.getVIM(),
        auto.getAutoModel().getId(),
        auto.getAutoTransmission().getId(),
        auto.getAutoEngine().getId());
  }
}
