package com.auto.practiceproject.controller.dto.request;

import com.auto.practiceproject.util.validation.AnnouncementDoesNotExist;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookmarkAnnouncementChangeDTO {

  @AnnouncementDoesNotExist
  @NotNull(message = "AnnouncementId should not be null")
  @Positive(message = "AnnouncementId should be positive")
  private Long AnnouncementId;
}
