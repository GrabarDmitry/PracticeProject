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
    private final AnnouncementService announcementService;

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

    public Announcement updateToEntity(Long id, AnnouncementRequestDTO updateDTO) {
        log.trace("AnnouncementUpdateDTO: {}, to Announcement", updateDTO);
        Announcement announcement = announcementService.findAnnouncement(id)
                .orElse(null);
        AutoModel autoModel = autoModelService.findAutoModel(updateDTO.getAutoModelId())
                .orElse(null);
        announcement.setTitle(autoModel.getAutoBrand().getTitle() + " " + autoModel.getTitle());
        announcement.setDescription(updateDTO.getDescription());
        announcement.setPhoneNumber(updateDTO.getPhoneNumber());
        announcement.setPrice(updateDTO.getPrice());
        announcement.setIsExchange(updateDTO.getIsExchange());
        announcement.setRegion(regionService.findRegion(updateDTO.getRegionId())
                .orElse(null));
        announcement.setCustomsDuty(updateDTO.getCustomsDuty());
        Auto auto = announcement.getAuto();
        auto.setAutoEngine(autoEngineService.findAutoEngine(updateDTO.getAutoEngineId())
                .orElse(null));
        auto.setAutoModel(autoModel);
        auto.setAutoTransmission(autoTransmissionService.findAutoTransmission(updateDTO.getAutoTransmissionId())
                .orElse(null));
        auto.setMileage(updateDTO.getMileage());
        auto.setEngineCapacity(updateDTO.getEngineCapacity());
        auto.setVIM(updateDTO.getVin());
        announcement.setAuto(auto);
        return announcement;
    }

    public Announcement toAnnouncementWithEditedIsActive(
            Long id,
            AnnouncementActiveChangeDTO activeActiveDTO
    ) {
        log.trace("AnnouncementActivityChangeDTO: {}, to Announcement", activeActiveDTO);
        Announcement announcement = announcementService.findAnnouncementById(id);
        announcement.setIsActive(activeActiveDTO.getIsActive());
        return announcement;
    }

    public Announcement toAnnouncementWithEditedIsModeration(
            Long id,
            AnnouncementModerationChangeDTO moderationChangeDTO
    ) {
        log.trace("AnnouncementModerationChangeDTO: {}, to announcement", moderationChangeDTO);
        Announcement announcement = announcementService.findAnnouncementById(id);
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
