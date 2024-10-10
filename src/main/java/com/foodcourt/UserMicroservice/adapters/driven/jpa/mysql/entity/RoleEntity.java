package com.foodcourt.UserMicroservice.adapters.driven.jpa.mysql.entity;

import com.foodcourt.UserMicroservice.adapters.util.AdaptersConstants;
import jakarta.persistence.*;

@Entity
@Table(name = AdaptersConstants.ROLE_TABLE_NAME)
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)

    private String name;

    public RoleEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public RoleEntity(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
