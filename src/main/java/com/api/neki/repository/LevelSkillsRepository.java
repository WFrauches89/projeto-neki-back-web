package com.api.neki.repository;

import com.api.neki.entities.LevelSkills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LevelSkillsRepository extends JpaRepository<LevelSkills, Long> {
    Optional<LevelSkills> findByLevelSkill(String levelSkill);
}
