package com.cjc.CMS.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.cjc.CMS.model.Course;
import com.cjc.CMS.service.CourseService;

 
@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // Add a new course
    @GetMapping("/add")
    public String showAddCourseForm(Model model) {
        model.addAttribute("course", new Course());
        return "add-course"; // return add-course.jsp
    }

    @PostMapping("/add")
    public String addCourse(@ModelAttribute("course") Course course) {
        courseService.addCourse(course);
        return "redirect:/courses/list";
    }

 
    @GetMapping("/list")
    public String listCourses(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "course-list"; // return course-list.jsp
    }
 
    @GetMapping("/update/{id}")
    public String showUpdateCourseForm(@PathVariable("id") int id, Model model) {
        Course course = courseService.getCourseById(id);
        if (course != null) {
            model.addAttribute("course", course);
            return "update-course"; // return update-course.jsp
        }
        return "redirect:/courses/list";
    }

    @PostMapping("/update/{id}")
    public String updateCourse(@PathVariable("id") int id, @ModelAttribute("course") Course course) {
        course.setId(id);
        courseService.addCourse(course); // Save updated course
        return "redirect:/courses/list";
    }

   
    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable("id") Long id) {
        courseService.deleteCourse(id);
        return "redirect:/courses/list";
    }

 
    @GetMapping("/search")
    public String searchByInstructor(@RequestParam("instructor") String instructor, Model model) {
        model.addAttribute("courses", courseService.getCoursesByInstructor(instructor));
        return "search-by-instructor"; // return search-by-instructor.jsp
    }
}
