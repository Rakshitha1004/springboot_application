package info.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import info.system.entity.Course;
import info.system.entity.Exam;
import info.system.entity.Student;
import info.system.repository.ExamRepository;
import info.system.service.CourseService;
import info.system.service.ExamService;
import info.system.service.StudentService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class Control {

    @Autowired
    StudentService studentService;

    @Autowired
    CourseService service;

    @GetMapping("/hello")
    public String hello() throws InterruptedException {
        // Thread.sleep(5000); // Simulate some processing delay
        String name = "OrderService";
        System.out.println("Hello from " + name);
        // Thread.sleep(5000); // Simulate some processing delay
        String name2 = "PaymentService";
        System.out.println("Hello from " + name2);
        // Thread.sleep(5000); // Simulate some processing delay
        System.out.println("Hello from " + name + " and " + name2);
        return "Hello from " + name + " and " + name2;
    }

    @PostMapping("/student")
    public Student createStudent(@RequestBody Student student) {

        return studentService.saveStudent(student);
    }

    @PostMapping("/course")
    public Course createCourse(@RequestBody Course c) {

        return service.saveCourse(c);
    }

    @GetMapping("/request")
    public String handleRequest() {
        return "Request received successfully";
    }

    @Autowired
    private ExamService examService;

    @PostMapping("/exam")
    public Exam saveExam(@RequestBody Exam exam) {

        return examService.saveExam(exam);
    }
}
