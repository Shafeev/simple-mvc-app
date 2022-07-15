package ru.mcs.mvc.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mcs.mvc.app.dao.PersonDAO;
import ru.mcs.mvc.app.models.Person;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final PersonDAO personalDAO;


    public AdminController(PersonDAO personalDAO) {
        this.personalDAO = personalDAO;
    }

    @GetMapping
    public String adminPage(Model model, @ModelAttribute("person")Person person) {
        model.addAttribute("people", personalDAO.index());

        return "adminPage";
    }

    @PatchMapping("/add")
    public String add(@ModelAttribute("person")Person person) {
        System.out.println(person.getId());

        return "redirect:/people";
    }

}
