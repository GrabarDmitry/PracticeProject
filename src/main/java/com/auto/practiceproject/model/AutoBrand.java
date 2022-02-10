package com.auto.practiceproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "auto_brand")
public class AutoBrand extends AbstractEntity {

  @Column(length = 45)
  private String title;
}
