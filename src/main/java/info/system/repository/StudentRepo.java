package info.system.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import info.system.entity.Student;
public interface StudentRepo extends JpaRepository<Student,Long>{
    
}
