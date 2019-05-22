package com.roman_musijowski.pgs_lessons.util.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    @Email
    @NotEmpty
    @Size(min = 8)
    private String userName;

    @NotEmpty
    @Size(min = 2, max = 36)
    private String name;

    @NotEmpty
    @Size(min = 2, max = 36)
    private String surName;

    private Integer yearOfStudies;

    @NotEmpty
    @Size(min = 5, max = 36)
    private String fieldOfStudy;

    @NotEmpty
    @Size(min = 11, max = 11)
    private String index;


    @Size(min = 6, max = 50)
    private String password;

    @Size(min = 6, max = 50)
    private String encryptedPassword;

    @JsonProperty("user_url")
    private String userUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public Integer getYearOfStudies() {
        return yearOfStudies;
    }

    public void setYearOfStudies(Integer yearOfStudies) {
        this.yearOfStudies = yearOfStudies;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public String getIndex() {
        return index;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }
}
