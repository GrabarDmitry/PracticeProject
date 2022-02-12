package com.auto.practiceproject.controller.converter;

import com.auto.practiceproject.controller.dto.request.AutoRequestDTO;
import com.auto.practiceproject.controller.dto.response.AutoResponseDTO;
import com.auto.practiceproject.model.Auto;
import com.auto.practiceproject.service.AutoEngineService;
import com.auto.practiceproject.service.AutoModelService;
import com.auto.practiceproject.service.AutoService;
import com.auto.practiceproject.service.AutoTransmissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AutoDTOConverter {

    private final AutoEngineService autoEngineService;
    private final AutoTransmissionService autoTransmissionService;
    private final AutoModelService autoModelService;
    private final AutoService autoService;

    public Auto toEntityCreate(AutoRequestDTO autoRequestDTO) {
        log.trace("Convert AutoRequestDTO: {}, to Auto", autoRequestDTO);
        return new Auto(
                autoRequestDTO.getMileage(),
                autoRequestDTO.getEngineCapacity(),
                autoRequestDTO.getVIM(),
                autoModelService.findAutoModel(autoRequestDTO.getAutoModelId())
                        .orElse(null),
                autoEngineService.findAutoEngine(autoRequestDTO.getAutoEngineId())
                        .orElse(null),
                autoTransmissionService.findAutoTransmission(autoRequestDTO.getAutoTransmissionId())
                        .orElse(null));
    }

    public Auto toEntityUpdate(Long autoId, AutoRequestDTO autoRequestDTO) {
        log.trace("Convert AutoRequestDTO: {}, to Auto", autoRequestDTO);
        Auto auto = autoService.findAuto(autoId)
                .orElse(null);

        auto.setMileage(autoRequestDTO.getMileage());
        auto.setEngineCapacity(autoRequestDTO.getEngineCapacity());
        auto.setVIM(autoRequestDTO.getVIM());
        auto.setAutoModel(autoModelService.findAutoModel(autoRequestDTO.getAutoModelId())
                .orElse(null));
        auto.setAutoEngine(autoEngineService.findAutoEngine(autoRequestDTO.getAutoEngineId())
                .orElse(null));
        auto.setAutoTransmission(autoTransmissionService.findAutoTransmission(autoRequestDTO.getAutoTransmissionId())
                .orElse(null));

        return auto;
    }


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
