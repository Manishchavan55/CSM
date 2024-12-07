package com.cjc.CMS.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cjc.CMS.model.Course;

import java.util.List;
import java.util.Optional;
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByInstructor(String instructor);

	Optional<Course> findById(int id);
}
