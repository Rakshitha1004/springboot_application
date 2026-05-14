package info.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.system.entity.Student;
import info.system.repository.StudentRepo;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepository;

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

}
