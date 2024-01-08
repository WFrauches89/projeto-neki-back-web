package com.api.neki.repository;

import com.api.neki.entities.Skills;
import com.api.neki.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SkillsRepository extends JpaRepository<Skills, Long> {

    @Query("SELECT s FROM Skills s WHERE s.user.id = :userId")
    Optional<List<Skills>> findByUserId(@Param("userId") Long userId);
}
