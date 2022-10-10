package net.cmderobertis.studentroster.repositories;

import net.cmderobertis.studentroster.models.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long> {
    List<Student> findAll();
}