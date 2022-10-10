package net.cmderobertis.studentroster.services;

import net.cmderobertis.studentroster.models.Student;
import net.cmderobertis.studentroster.models.Course;
import net.cmderobertis.studentroster.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository repo;
    StudentService(StudentRepository repo) {
        this.repo = repo;
    }
    // CREATE
    public void create(Student student) {
        repo.save(student);
    }
    // READ
    public List<Student> getAll() {
        return repo.findAll();
    }
    public Student getOne(Long id) {
        Optional<Student> person = repo.findById(id);
        return person.orElse(null);
    }
    // UPDATE
    public void update(Student student) {
        repo.save(student);
    }
    //DELETE
    public void delete(Long id) {
        repo.deleteById(id);
    }
    public void addCourse(Course course, Long id) {
        Student student = repo.findById(id).get();
        student.getCourses().add(course);
        repo.save(student);
    }
    public void removeCourse(Course course, Long id) {
        Student student = repo.findById(id).get();
        student.getCourses().remove(course);
        repo.save(student);
    }
}
