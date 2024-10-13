package com.foodcourt.UserMicroservice.adapters.driven.jpa.mysql.entity;

import com.foodcourt.UserMicroservice.adapters.util.AdaptersConstants;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = AdaptersConstants.USER_TABLE_NAME)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private Integer idDocument;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = true)
    private LocalDate dateOfBirth;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = AdaptersConstants.USER_ROLE_JOIN_COLUMN, nullable = false)
    private RoleEntity roleId;

    public UserEntity(Long id, String name, String lastName, Integer idDocument, String phone, LocalDate dateOfBirth, String email, String password, RoleEntity roleId) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.idDocument = idDocument;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.roleId = roleId;
    }
    public UserEntity(){}

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getIdDocument() {
        return idDocument;
    }

    public void setIdDocument(Integer idDocument) {
        this.idDocument = idDocument;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleEntity getRoleId() {
        return roleId;
    }

    public void setRoleId(RoleEntity roleId) {
        this.roleId = roleId;
    }
}
