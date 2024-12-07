package com.cjc.CMS.service;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.CMS.model.Course;
import com.cjc.CMS.repository.CourseRepository;
@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(int id) {
        return courseRepository.findById(id).orElse(null);
    }

    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(int id, Course courseDetails) {
    	Course course= getCourseById(id);
        if (course != null) {
            course.setCourseName(courseDetails.getCourseName());
            course.setFee(courseDetails.getFee());
            course.setInstructor(courseDetails.getInstructor());
            return courseRepository.save(course);
        }
        return null;
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    public List<Course> getCoursesByInstructor(String instructor) {
        return courseRepository.findByInstructor(instructor);
    }
}
