package info.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.system.entity.Course;
import info.system.repository.CourseRepo;

@Service
public class CourseService {

    @Autowired
    private CourseRepo repo;

    public Course saveCourse(Course c) {
        return repo.save(c);
    }
}
