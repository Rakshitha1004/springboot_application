package info.system.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "exam")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "exam_name", nullable = false)
    private String examName;

    @Column(nullable = false)
    private String subject;

    @Column(name = "total_marks")
    private Integer totalMarks;

    @Column(name = "duration_minutes")
    private Integer duration;

    @Column(name = "exam_date")
    private LocalDate examDate;

    // Default Constructor
    public Exam() {
    }

    // Parameterized Constructor
    public Exam(Long id, String examName, String subject,
                Integer totalMarks, Integer duration,
                LocalDate examDate) {
        this.id = id;
        this.examName = examName;
        this.subject = subject;
        this.totalMarks = totalMarks;
        this.duration = duration;
        this.examDate = examDate;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(Integer totalMarks) {
        this.totalMarks = totalMarks;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
    }
}