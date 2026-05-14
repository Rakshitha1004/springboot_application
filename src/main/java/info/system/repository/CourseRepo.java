package info.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import info.system.entity.Course;

public interface CourseRepo extends JpaRepository<Course, Long> {

}
