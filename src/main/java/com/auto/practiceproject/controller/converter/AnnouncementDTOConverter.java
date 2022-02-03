package com.auto.practiceproject.controller.converter;

import com.auto.practiceproject.controller.dto.request.AnnouncementActiveChangeDTO;
import com.auto.practiceproject.controller.dto.request.AnnouncementModerationChangeDTO;
import com.auto.practiceproject.controller.dto.request.AnnouncementRequestDTO;
import com.auto.practiceproject.controller.dto.response.AnnouncementResponseDTO;
import com.auto.practiceproject.model.Announcement;
import com.auto.practiceproject.model.Auto;
import com.auto.practiceproject.model.AutoModel;
import com.auto.practiceproject.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AnnouncementDTOConverter {

    private final UserService userService;
    private final RegionService regionService;
    private final AutoEngineService autoEngineService;
    private final AutoTransmissionService autoTransmissionService;
    private final AutoModelService autoModelService;

    public AnnouncementResponseDTO toDTO(Announcement announcement) {
        log.trace("Convert Announcement: {}, to AnnouncementResponseDTO", announcement);
        return new AnnouncementResponseDTO(
                announcement.getId(),
                announcement.getTitle(),
                announcement.getDescription(),
                announcement.getPhoneNumber(),
                announcement.getPrice(),
                announcement.getIsActive(),
                announcement.getIsExchange(),
                announcement.getCustomsDuty(),
                announcement.getUser().getId(),
                announcement.getRegion().getId(),
                announcement.getAuto().getId()
        );
    }

    public Announcement toEntity(AnnouncementRequestDTO createDTO) {
        log.trace("AnnouncementRequestDTO: {}, to Announcement", createDTO);
        AutoModel autoModel = autoModelService.findAutoModel(createDTO.getAutoModelId())
                .orElse(null);
        return new Announcement(
                autoModel.getAutoBrand().getTitle() + " " + autoModel.getTitle(),
                createDTO.getDescription(),
                createDTO.getPhoneNumber(),
                createDTO.getPrice(),
                true,
                true,
                1,
                5.0,
                createDTO.getIsExchange(),
                createDTO.getCustomsDuty(),
                createAutoUtil(createDTO.getMileage(), createDTO.getEngineCapacity(), createDTO.getVin(),
                        autoModel, createDTO.getAutoEngineId(), createDTO.getAutoTransmissionId()),
                userService.getCurrentUser()
                        .orElse(null),
                regionService.findRegion(createDTO.getRegionId())
                        .orElse(null)
        );
    }

    public Announcement updateToEntity(
            Long id,
            AnnouncementRequestDTO updateDTO
    ) {
        log.trace("AnnouncementRequestDTO: {}, to Announcement", updateDTO);
        Announcement announcement = toEntity(updateDTO);
        announcement.setId(id);
        return announcement;
    }

    public Announcement toDTOWithEditedIsExchange(
            Announcement announcement,
            AnnouncementActiveChangeDTO activeChangeDTO
    ) {
        log.trace("AnnouncementActivityChangeDTO: {}, to Announcement", activeChangeDTO);
        announcement.setIsExchange(activeChangeDTO.getIsExchange());
        return announcement;
    }

    public Announcement toDTOWithEditedIsModeration(
            Announcement announcement,
            AnnouncementModerationChangeDTO moderationChangeDTO
    ) {
        log.trace("AnnouncementModerationChangeDTO: {}, to announcement", moderationChangeDTO);
        announcement.setIsModeration(moderationChangeDTO.getIsModeration());
        return announcement;
    }

    private Auto createAutoUtil(
            Integer mileage, Integer engineCapacity, String vim, AutoModel autoModel, Long autoEngineId, Long autoTransmissionId) {
        return new Auto(
                mileage,
                engineCapacity,
                vim,
                autoModel,
                autoEngineService.findAutoEngine(autoEngineId)
                        .orElse(null),
                autoTransmissionService.findAutoTransmission(autoTransmissionId)
                        .orElse(null)
        );
    }

}
