package com.avr.ClassRelationships.Association;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;

import org.junit.jupiter.api.Test;

import com.avr.ClassRelationships.Association.OnlineCoursePlatform;

// java



class OnlineCoursePlatformTest {

    @Test
    void instructorAddCourse_setsInstructorAndAddsToList() {
        OnlineCoursePlatform platform = new OnlineCoursePlatform();
        OnlineCoursePlatform.Instructor instructor = platform.new Instructor("Alice");
        OnlineCoursePlatform.Course course = platform.new Course("Math 101");

        // initially no instructor on course
        assertNull(course.getInstructor(), "Course should not have an instructor before being added");

        instructor.addCourse(course);

        // after adding, course should reference instructor and instructor should have the course
        assertEquals(instructor, course.getInstructor(), "Course instructor should be set by addCourse");
        assertNotNull(instructor.getCourses(), "Instructor courses list should be initialized");
        assertEquals(1, instructor.getCourses().size(), "Instructor should have exactly one course after addCourse");
        assertTrue(instructor.getCourses().contains(course), "Instructor courses should contain the added course");
    }

    @Test
    void addMultipleCourses_preservesAllAndSetsInstructor() {
        OnlineCoursePlatform platform = new OnlineCoursePlatform();
        OnlineCoursePlatform.Instructor instructor = platform.new Instructor("Dr X");
        OnlineCoursePlatform.Course c1 = platform.new Course("Biology");
        OnlineCoursePlatform.Course c2 = platform.new Course("Chemistry");

        instructor.addCourse(c1);
        instructor.addCourse(c2);

        List<OnlineCoursePlatform.Course> courses = instructor.getCourses();
        assertNotNull(courses, "Courses list should be initialized");
        assertEquals(2, courses.size(), "Instructor should have two courses");
        assertEquals(c1, courses.get(0), "First course preserved");
        assertEquals(c2, courses.get(1), "Second course preserved");
        assertEquals(instructor, c1.getInstructor(), "First course instructor set correctly");
        assertEquals(instructor, c2.getInstructor(), "Second course instructor set correctly");
    }

    @Test
    void studentGetInstructorName_returnsInstructorNameWhenEnrolled() throws DatatypeConfigurationException {
        OnlineCoursePlatform platform = new OnlineCoursePlatform();
        OnlineCoursePlatform.Instructor instructor = platform.new Instructor("Dr Who");
        OnlineCoursePlatform.Course course = platform.new Course("Time Travel");
        instructor.addCourse(course);

        OnlineCoursePlatform.Student student = platform.new Student("Bob");
        student.setEnrolledCourse(course);

        String instructorName = student.getInstructorName();
        assertEquals("Dr Who", instructorName, "Student should return the instructor's name via enrolled course");
    }

    @Test
    void studentGetInstructorName_throwsWhenNotEnrolled() {
        OnlineCoursePlatform platform = new OnlineCoursePlatform();
        OnlineCoursePlatform.Student student = platform.new Student("Charlie");

        assertThrows(DatatypeConfigurationException.class, () -> student.getInstructorName(),
                "getInstructorName should throw when student has no enrolled course");
    }

    @Test
    void courseConstructor_withInstructor_doesNotMutateInstructorCoursesList() {
        OnlineCoursePlatform platform = new OnlineCoursePlatform();
        OnlineCoursePlatform.Instructor instructor = platform.new Instructor("Prof X");

        // construct course with instructor set directly (all-args constructor)
        OnlineCoursePlatform.Course course = platform.new Course("Mutants 101", instructor);

        // course knows its instructor
        assertEquals(instructor, course.getInstructor(), "Course constructed with instructor should reference it");

        // but instructor's courses list should not be automatically populated by setting course.instructor
        assertTrue(instructor.getCourses() == null || instructor.getCourses().isEmpty(),
                "Instructor's courses should not be populated implicitly when course's instructor is set externally");

        // adding via addCourse should both add to instructor list and keep course instructor reference
        instructor.addCourse(course);
        assertNotNull(instructor.getCourses(), "Instructor courses list should be initialized after addCourse");
        assertTrue(instructor.getCourses().contains(course), "Instructor courses should contain the course after addCourse");
        assertEquals(instructor, course.getInstructor(), "Course instructor should remain the same after addCourse");
    }
}