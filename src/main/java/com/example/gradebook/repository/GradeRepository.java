package com.example.gradebook.repository;


import com.example.gradebook.models.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade,Long> {
    List<Grade> findByUserId(Long postId);
}
