package com.api.neki.entities;

import javax.persistence.*;

@Entity
@Table(name = "skills")
public class Skills {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSkill")
    private Long Id;

    @Column(nullable = false)
    private String nameSkill;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "level_skill_id", nullable = false)
    private LevelSkills level;

    public Skills() {
    }

    public Skills(Long id, String nameSkill, String description, User user, LevelSkills level) {
        Id = id;
        this.nameSkill = nameSkill;
        this.description = description;
        this.user = user;
        this.level = level;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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

    public LevelSkills getLevel() {
        return level;
    }

    public void setLevel(LevelSkills level) {
        this.level = level;
    }

}
