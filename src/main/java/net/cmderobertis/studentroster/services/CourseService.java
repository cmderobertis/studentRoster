package net.cmderobertis.studentroster.services;

import net.cmderobertis.studentroster.models.Student;
import net.cmderobertis.studentroster.models.Course;
import net.cmderobertis.studentroster.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository repo;
    public CourseService(CourseRepository repo) {this.repo = repo;}
    // CREATE
    public void create(Course course) {repo.save(course);}
    // READ
    public List<Course> getAll() {return repo.findAll();}
    public Course getOne(Long id) {
        Optional<Course> license = repo.findById(id);
        return license.orElse(null);
    }
    // UPDATE
    public void update(Course course) {repo.save(course);}
    //DELETE
    public void delete(Long id) {repo.deleteById(id);}
    // ADD STUDENT
    public void addStudent(Student student, Long id) {
        Course course = repo.findById(id).get();
        course.getStudents().add(student);
        repo.save(course);
    }
    // REMOVE STUDENT
    public void removeStudent(Student student, Long id) {
        Course course = repo.findById(id).get();
        course.getStudents().remove(student);
        repo.save(course);
    }
}
