package com.crickinfo.crickinfo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseEntity {
    private String roleName;
    private String description;

    public Role(String roleName) {
        this.roleName = roleName;
    }
}