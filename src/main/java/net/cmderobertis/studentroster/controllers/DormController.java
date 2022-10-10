package net.cmderobertis.studentroster.controllers;
import net.cmderobertis.studentroster.models.Dorm;
import net.cmderobertis.studentroster.services.DormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Controller
public class DormController {
    @Autowired
    DormService service;
    @GetMapping("/dorms/new")
    String createForm(@ModelAttribute("dorm") Dorm dorm) {
        return "createDorm.jsp";
    }
    @PostMapping("/dorms")
    String create(@ModelAttribute("dorm") Dorm dorm) {
        service.create(dorm);
        return "redirect:/dorms";
    }
    @GetMapping("/dorms")
    String showAll(Model model) {
        List<Dorm> dorms = service.getAll();
        model.addAttribute("dorms", dorms);
        return "dorms.jsp";
    }
    @GetMapping("/dorms/{id}")
    String showOne(@PathVariable Long id, Model model) {
        Dorm dorm = service.getOne(id);
        model.addAttribute("dorm", dorm);
        model.addAttribute("students", dorm.getStudents());
        return "showDorm.jsp";
    }
    @GetMapping("/dorms/{id}/edit")
    String updateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("dorm", service.getOne(id));
        return "updateDorm.jsp";
    }
    @PutMapping("/dorms/{id}")
    String update(
            @Valid
            @ModelAttribute("dorm") Dorm dorm,
            BindingResult result) {
        if (result.hasErrors()) {
            return "updateDorm.jsp";
        } else {
            service.update(dorm);
            return "redirect:/dorms";
        }
    }
    @DeleteMapping("/dorms/{id}")
    String delete(@PathVariable("id") Long id) {
        service.delete(id);
        return "redirect:/dorms";
    }
}
