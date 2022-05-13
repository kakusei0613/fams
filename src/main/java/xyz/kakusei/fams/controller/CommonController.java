package xyz.kakusei.fams.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import xyz.kakusei.fams.entity.Order;
import xyz.kakusei.fams.service.IEmployeeService;
import xyz.kakusei.fams.service.IOrderService;
import xyz.kakusei.fams.util.LoginException;
import xyz.kakusei.fams.util.RequiredPermission;
import xyz.kakusei.fams.util.SystemSetting;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommonController {

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private SystemSetting setting;




    @GetMapping("/login")
    public String login() {
        return "/login";
    }
    @PostMapping("/login")
    public String login(String username, String password) {
        try {
            employeeService.login(username, password);
            return "redirect:/index";
        }
        catch (LoginException e) {
            e.printStackTrace();
            return "redirect:/login";
        }

    }

    @GetMapping("/logout")
    public String logout() {
        employeeService.logout();
        return "redirect:/login";
    }

    @GetMapping("/index")
    public String index(Model model) {
        Logger logger = LoggerFactory.getLogger(this.getClass());
//        PageHelper.startPage(1, setting.getPageSize());
        model.addAttribute("completedOrders", new PageInfo<Order>(orderService.queryOrdersCompleted()));
        PageHelper.startPage(1, setting.getPageSize());
//        model.addAttribute("incompleteOrders", new PageInfo<Order>(orderService.queryIncompleteOrder()));
        model.addAttribute("pageResult", new PageInfo<Order>(orderService.queryIncompleteOrder()));
        return "index";
    }


    @GetMapping("/needPermission")
    public String noPermission() {
        return "/error/needPermission";
    }

    @GetMapping("/report")
    @RequiredPermission({"Report","report:used"})
    public String report() {
        return "report";
    }
}
