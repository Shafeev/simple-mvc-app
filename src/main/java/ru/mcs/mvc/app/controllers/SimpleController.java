package ru.mcs.mvc.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mcs.mvc.app.CalculatorAction;

@Controller
@RequestMapping("/first")
public class SimpleController {

    @GetMapping("/simple")
    public String simple() {
        return "simple";
    }

    @GetMapping("/hello")
    public String helloPage(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "surname", required = false) String surname,
                            Model model) {
        // System.out.println("hello " + name + " " + surname);
        model.addAttribute("message", "hello " + name + " " + surname);
        return "first/hello";
    }

    @GetMapping("/goodbye")
    public String goodByePage() {
        return "first/goodbye";
    }

    @GetMapping("/calculator")
    public String calculator(@RequestParam(value = "a", required = false) int a,
                             @RequestParam(value = "b", required = false) int b,
                             @RequestParam(value = "action", required = false) String action,
                             Model model) {
        double result = 0;
        System.out.println("action " + action);
        CalculatorAction calcAction = CalculatorAction.valueOf(action.toUpperCase());
        System.out.println(calcAction);
        switch (calcAction) {
            case ADDITION:
                result = a + b;
                break;
            case DIVISION:
                result = a / b;
                break;
            case SUBTRACTION:
                result = a - b;
                break;
            case MULTIPLICATION:
                result = a * b;
                break;
        }
        model.addAttribute("message", "result = " + result);
        return "first/calculator";
    }

}
