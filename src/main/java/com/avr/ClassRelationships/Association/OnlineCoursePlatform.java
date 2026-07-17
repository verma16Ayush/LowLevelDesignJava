package com.avr.ClassRelationships.Association;

import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

public class OnlineCoursePlatform {

    @AllArgsConstructor
    @RequiredArgsConstructor
    @Getter
    @Setter
    class Instructor {
        private final String name;
        private List<Course> courses;

        public void addCourse(Course course) {
            if(this.courses == null) {
                this.courses = new ArrayList<>();
            }
            course.setInstructor(this);
            this.courses.add(course);
        }
    }

    @AllArgsConstructor
    @RequiredArgsConstructor
    @Getter
    @Setter
    class Course {
        private final String name;
        private Instructor instructor;
    }

    @AllArgsConstructor
    @RequiredArgsConstructor
    @Getter
    @Setter
    class Student {
        private final String name;
        private Course enrolledCourse;

        public String getInstructorName() throws DatatypeConfigurationException {
            if(this.getEnrolledCourse() == null)
                throw new DatatypeConfigurationException();
            return this.getEnrolledCourse().getInstructor().getName();
        }
    }

}
