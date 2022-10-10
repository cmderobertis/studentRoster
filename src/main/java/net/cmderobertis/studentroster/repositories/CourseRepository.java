package net.cmderobertis.studentroster.repositories;

import net.cmderobertis.studentroster.models.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, Long> {
    List<Course> findAll();
}
