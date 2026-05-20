package info.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import info.system.entity.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long> {

}