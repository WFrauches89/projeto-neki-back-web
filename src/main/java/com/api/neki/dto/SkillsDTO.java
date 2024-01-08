package com.api.neki.dto;

import com.api.neki.entities.User;


public class SkillsDTO {

    private String nameSkill;
    private String description;
    private User user;
    private String level;

    public SkillsDTO(String nameSkill, String description,
                     User user, String level) {
        this.nameSkill = nameSkill;
        this.description = description;
        this.user = user;
        this.level = level;
    }

    public SkillsDTO() {
    }

    public String getNameSkill() {
        return nameSkill;
    }

    public void setNameSkill(String nameSkill) {
        this.nameSkill = nameSkill;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
