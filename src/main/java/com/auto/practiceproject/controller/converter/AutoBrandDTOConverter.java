package com.auto.practiceproject.controller.converter;

import com.auto.practiceproject.controller.dto.response.AutoBrandResponseDTO;
import com.auto.practiceproject.model.AutoBrand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AutoBrandDTOConverter {

  public AutoBrandResponseDTO toDTO(AutoBrand autoBrand) {
    log.trace("Convert AutoBrand: {}, to AutoBrandResponseDTO", autoBrand);
    return new AutoBrandResponseDTO(autoBrand.getId(), autoBrand.getTitle());
  }
}
