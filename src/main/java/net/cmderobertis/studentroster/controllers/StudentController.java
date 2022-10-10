package net.cmderobertis.studentroster.controllers;
import net.cmderobertis.studentroster.models.Student;
import net.cmderobertis.studentroster.models.Course;
import net.cmderobertis.studentroster.services.DormService;
import net.cmderobertis.studentroster.services.StudentService;
import net.cmderobertis.studentroster.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class StudentController {
    @Autowired
    StudentService service;
    @Autowired
    CourseService courseService;
    @Autowired
    DormService dormService;

    @GetMapping("/")
    String index() {
        return "redirect:/dorms";
    }

    @GetMapping("/students/new")
    String createForm(Model model, @ModelAttribute("student") Student student) {
        model.addAttribute("dorms", dormService.getAll().stream().filter((dorm) -> !dorm.getStudents().contains(student)).collect(Collectors.toList()));
        return "createStudent.jsp";
    }
    @PostMapping("/students")
    String create(@ModelAttribute("student") Student student) {
        service.create(student);
        return "redirect:/";
    }
    @GetMapping("/students")
    String showAll(Model model) {
        List<Student> students = service.getAll();
        model.addAttribute("students", students);
        return "students.jsp";
    }
    @GetMapping("/students/{id}")
    String showOne(@PathVariable("id") Long id, Model model) {
        Student student = service.getOne(id);
        model.addAttribute("otherCourses", courseService.getAll().stream().filter((course) -> !course.getStudents().contains(student)).collect(Collectors.toList()));
        model.addAttribute("student", student);
        return "showStudent.jsp";
    }
    @GetMapping("/students/{id}/edit")
    String updateForm(@PathVariable("id") Long id, Model model) {
        Student student = service.getOne(id);
        model.addAttribute("student", student);
        model.addAttribute("dorms", dormService.getAll().stream().filter((dorm) -> !dorm.getStudents().contains(student)).collect(Collectors.toList()));
        return "updateStudent.jsp";
    }
    @PutMapping("/students/{id}")
    String update(
            @Valid
            @ModelAttribute("student") Student student,
            BindingResult result) {
        if (result.hasErrors()) {
            return "updateStudent.jsp";
        } else {
            service.update(student);
            return "redirect:/";
        }
    }
    @DeleteMapping("/students/{id}")
    String delete(@PathVariable("id") Long id) {
        service.delete(id);
        return "redirect:/";
    }
    @PostMapping("/students/{id}/add")
    String add(@PathVariable("id") Long id, @RequestParam("courseId") Long courseId) {
        Course course = courseService.getOne(courseId);
        service.addCourse(course, id);
        return "redirect:/students/" + id;
    }
    @GetMapping("/students/{id}/remove/{courseId}")
    String remove(@PathVariable("id") Long id, @PathVariable("courseId") Long courseId) {
        Course course = courseService.getOne(courseId);
        service.removeCourse(course, id);
        return "redirect:/students/" + id;
    }
}
