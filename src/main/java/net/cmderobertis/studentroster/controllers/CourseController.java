package net.cmderobertis.studentroster.controllers;

import net.cmderobertis.studentroster.models.Student;
import net.cmderobertis.studentroster.models.Course;
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
public class CourseController {
    @Autowired
    CourseService service;
    @Autowired
    StudentService studentService;

    @GetMapping("/courses/new")
    String createForm(Model model, @ModelAttribute("course") Course course) {
        return "createCourse.jsp";
    }

    @PostMapping("/courses")
    String create(
            @Valid
            @ModelAttribute("course") Course course,
            BindingResult result) {
        if (result.hasErrors()) {
            return "createCourse.jsp";
        } else {
            service.create(course);
            return "redirect:/";
        }
    }

    @GetMapping("/courses")
    String showAll(Model model) {
        List<Course> course = service.getAll();
        model.addAttribute("courses", course);
        return "courses.jsp";
    }
    @GetMapping("/courses/{id}")
    String showOne(@PathVariable("id") Long id, Model model) {
        Course course = service.getOne(id);
        model.addAttribute("otherStudents", studentService.getAll().stream().filter((student) -> !student.getCourses().contains(course)).collect(Collectors.toList()));
        model.addAttribute("course", course);
        return "showCourse.jsp";
    }
    @GetMapping("/courses/{id}/edit")
    String updateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("course", service.getOne(id));
        return "updateCourse.jsp";
    }
    @PutMapping("/courses/{id}")
    String update(
            @Valid
            @ModelAttribute("course") Course course,
            BindingResult result) {
        if (result.hasErrors()) {
            return "updateCourse.jsp";
        } else {
            service.update(course);
            return "redirect:/courses";
        }
    }
    @DeleteMapping("/courses/{id}")
    String delete(@PathVariable("id") Long id) {
        service.delete(id);
        return "redirect:/";
    }
    @PostMapping("/courses/{id}/add")
    String add(@PathVariable("id") Long id, @RequestParam("studentId") Long studentId) {
        Student student = studentService.getOne(studentId);
        service.addStudent(student, id);
        return "redirect:/courses/" + id;
    }
    @GetMapping("/courses/{id}/remove/{studentId}")
    String remove(@PathVariable("id") Long id, @PathVariable("studentId") Long studentId) {
        Student student = studentService.getOne(studentId);
        service.removeStudent(student, id);
        return "redirect:/courses/" + id;
    }
}