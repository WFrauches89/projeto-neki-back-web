package com.api.neki.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "LevelSkills")
public class LevelSkills {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLevelSkill")
    private Long Id;

    @Column(nullable = false)
    private String levelSkill;

    @OneToMany(mappedBy = "level", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Skills> skills;

    public LevelSkills() {
    }

    public LevelSkills(Long id, String levelSkill, List<Skills> skills) {
        Id = id;
        this.levelSkill = levelSkill;
        this.skills = skills;
    }

    public LevelSkills(String level) {
        this.levelSkill = level;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getLevelSkill() {
        return levelSkill;
    }

    public void setLevelSkill(String levelSkill) {
        this.levelSkill = levelSkill;
    }

    public List<Skills> getSkills() {
        return skills;
    }

    public void setSkills(List<Skills> skills) {
        this.skills = skills;
    }

}
