package xyz.kakusei.fams.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import xyz.kakusei.fams.service.IEmployeeService;
import xyz.kakusei.fams.util.LoginException;

@Controller
public class CommonController {

    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("/login")
    public String login() {
        return "/login";
    }
    @PostMapping("/login")
    public String login(String username, String password) {
        try {
            employeeService.login(username, password);
            return "redirect:/employee/tables";
        }
        catch (LoginException e) {
            e.printStackTrace();
            return "redirect:/login";
        }

    }
    @GetMapping("/needPermission")
    public String noPermission() {
        return "/error/needPermission";
    }
}
