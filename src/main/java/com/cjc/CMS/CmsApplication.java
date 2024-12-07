package com.cjc.CMS;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cjc.CMS.model.Course;
import com.cjc.CMS.service.CourseService;

@SpringBootApplication
public class CmsApplication implements CommandLineRunner {

    @Autowired
    private CourseService courseService;

    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nCourse Management System");
            System.out.println("1. Add a New Course");
            System.out.println("2. View All Courses");
            System.out.println("3. Update Course Details");
            System.out.println("4. Delete a Course");
            System.out.println("5. Retrieve Courses by Instructor");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character after the number input

            switch (choice) {
                case 1:
                    System.out.print("Enter Course Name: ");
                    String courseName = scanner.nextLine();
                    System.out.print("Enter Fee: ");
                    Double fee = scanner.nextDouble();
                    scanner.nextLine();  // Consume the newline
                    System.out.print("Enter Instructor: ");
                    String instructor = scanner.nextLine();
                    
                    Course course = new Course();
                    course.setCourseName(courseName);
                    course.setFee(fee);
                    course.setInstructor(instructor);
                    courseService.addCourse(course);
                    System.out.println("Course added successfully!");
                    break;

                case 2:
                    List<Course> courses = courseService.getAllCourses();
                    System.out.println("All Courses:");
                    for (Course c : courses) {
                        System.out.println("ID: " + c.getId() + ", Name: " + c.getCourseName() + 
                                           ", Fee: " + c.getFee() + ", Instructor: " + c.getInstructor());
                    }
                    break;

                case 3:
                    System.out.print("Enter Course ID to update: ");
                    int courseId = scanner.nextInt();
                    scanner.nextLine();  // Consume the newline

                    System.out.print("Enter New Course Name: ");
                    String newCourseName = scanner.nextLine();
                    System.out.print("Enter New Fee: ");
                    Double newFee = scanner.nextDouble();
                    scanner.nextLine();  // Consume the newline
                    System.out.print("Enter New Instructor: ");
                    String newInstructor = scanner.nextLine();

                    Course updatedCourse = new Course();
                    updatedCourse.setCourseName(newCourseName);
                    updatedCourse.setFee(newFee);
                    updatedCourse.setInstructor(newInstructor);

                    Course updated = courseService.updateCourse(courseId, updatedCourse);
                    if (updated != null) {
                        System.out.println("Course updated successfully!");
                    } else {
                        System.out.println("Course not found!");
                    }
                    break;

                case 4:
                    System.out.print("Enter Course ID to delete: ");
                    Long deleteId = scanner.nextLong();
                    courseService.deleteCourse(deleteId);
                    System.out.println("Course deleted successfully!");
                    break;

                case 5:
                    System.out.print("Enter Instructor Name: ");
                    String searchInstructor = scanner.nextLine();
                    List<Course> coursesByInstructor = courseService.getCoursesByInstructor(searchInstructor);
                    System.out.println("Courses by Instructor " + searchInstructor + ":");
                    for (Course c : coursesByInstructor) {
                        System.out.println("ID: " + c.getId() + ", Name: " + c.getCourseName() + 
                                           ", Fee: " + c.getFee());
                    }
                    break;

                case 6:
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
                    break;
            }
        }
    }
}

