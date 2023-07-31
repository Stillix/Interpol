package com.example.interpol.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "roles")
public class Role {
  @Id
  @Column(name = "id_role")
  private Long id;
  @Column(name = "role_name")
  private String roleName;
}
