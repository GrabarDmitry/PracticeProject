package com.auto.practiceproject.controller.converter;

import com.auto.practiceproject.controller.dto.response.RegionResponseDTO;
import com.auto.practiceproject.model.Region;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegionDTOConverter {

  public RegionResponseDTO toDTO(Region region) {
    log.trace("Convert Region: {}, to RegionResponseDTO", region);
    return new RegionResponseDTO(region.getId(), region.getTitle());
  }
}
