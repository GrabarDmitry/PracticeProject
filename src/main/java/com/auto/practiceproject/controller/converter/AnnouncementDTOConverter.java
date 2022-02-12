package com.auto.practiceproject.controller.converter;

import com.auto.practiceproject.controller.dto.request.AnnouncementActiveChangeDTO;
import com.auto.practiceproject.controller.dto.request.AnnouncementModerationChangeDTO;
import com.auto.practiceproject.controller.dto.request.AnnouncementRequestDTO;
import com.auto.practiceproject.controller.dto.request.AutoRequestDTO;
import com.auto.practiceproject.controller.dto.response.AnnouncementResponseDTO;
import com.auto.practiceproject.model.Announcement;
import com.auto.practiceproject.model.Auto;
import com.auto.practiceproject.service.AnnouncementService;
import com.auto.practiceproject.service.RegionService;
import com.auto.practiceproject.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AnnouncementDTOConverter {

  private final UserService userService;
  private final RegionService regionService;
  private final AnnouncementService announcementService;
  private final AutoDTOConverter autoDTOConverter;

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
        announcement.getAuto().getId());
  }

  public Announcement toEntity(AnnouncementRequestDTO createDTO) {
    log.trace("AnnouncementRequestDTO: {}, to Announcement", createDTO);
    Auto auto =
        autoDTOConverter.toEntityCreate(
            new AutoRequestDTO(
                createDTO.getMileage(),
                createDTO.getEngineCapacity(),
                createDTO.getVin(),
                createDTO.getAutoModelId(),
                createDTO.getAutoEngineId(),
                createDTO.getAutoTransmissionId()));
    return new Announcement(
        auto.getAutoModel().getAutoBrand().getTitle() + " " + auto.getAutoModel().getTitle(),
        createDTO.getDescription(),
        createDTO.getPhoneNumber(),
        createDTO.getPrice(),
        true,
        true,
        1,
        5.0,
        createDTO.getIsExchange(),
        createDTO.getCustomsDuty(),
        auto,
        userService.getCurrentUser().orElse(null),
        regionService.findRegion(createDTO.getRegionId()).orElse(null));
  }

  public Announcement updateToEntity(Long id, AnnouncementRequestDTO updateDTO) {
    log.trace("AnnouncementUpdateDTO: {}, to Announcement", updateDTO);

    Announcement announcement = announcementService.findAnnouncement(id).orElse(null);

    Auto auto =
        autoDTOConverter.toEntityUpdate(
            announcement.getAuto().getId(),
            new AutoRequestDTO(
                updateDTO.getMileage(),
                updateDTO.getEngineCapacity(),
                updateDTO.getVin(),
                updateDTO.getAutoModelId(),
                updateDTO.getAutoEngineId(),
                updateDTO.getAutoTransmissionId()));

    announcement.setTitle(
        auto.getAutoModel().getAutoBrand().getTitle() + " " + auto.getAutoModel().getTitle());
    announcement.setDescription(updateDTO.getDescription());
    announcement.setPhoneNumber(updateDTO.getPhoneNumber());
    announcement.setPrice(updateDTO.getPrice());
    announcement.setIsModeration(true);
    announcement.setIsExchange(updateDTO.getIsExchange());
    announcement.setRegion(regionService.findRegion(updateDTO.getRegionId()).orElse(null));
    announcement.setCustomsDuty(updateDTO.getCustomsDuty());
    announcement.setAuto(auto);
    return announcement;
  }

  public Announcement toAnnouncementWithEditedIsActive(
      Long id, AnnouncementActiveChangeDTO activeActiveDTO) {
    log.trace("AnnouncementActivityChangeDTO: {}, to Announcement", activeActiveDTO);
    Announcement announcement = announcementService.findAnnouncementById(id);
    announcement.setIsActive(activeActiveDTO.getIsActive());
    return announcement;
  }

  public Announcement toAnnouncementWithEditedIsModeration(
      Long id, AnnouncementModerationChangeDTO moderationChangeDTO) {
    log.trace("AnnouncementModerationChangeDTO: {}, to announcement", moderationChangeDTO);
    Announcement announcement = announcementService.findAnnouncementById(id);
    announcement.setIsModeration(moderationChangeDTO.getIsModeration());
    return announcement;
  }
}
