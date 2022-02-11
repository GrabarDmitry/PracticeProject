package com.auto.practiceproject.controller.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserResponseDTO {
  private Long id;
  private String email;
  private String name;
  private String surname;
  private Long walletId;
}
