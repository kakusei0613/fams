package xyz.kakusei.fams.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.kakusei.fams.service.IPermissionService;
import xyz.kakusei.fams.util.RequiredPermission;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @RequiredPermission({"Query Permission","permission:query"})
    @GetMapping("/tables")
    public String tables(Model model) {
        model.addAttribute("permissions", permissionService.queryAll());
        return "/permission/tables";
    }

    @RequiredPermission({"Reload Permission","permission:reload"})
    @GetMapping("/reload")
    public String reload() {
        permissionService.reload();
        return "redirect:/permission/tables";
    }
}
