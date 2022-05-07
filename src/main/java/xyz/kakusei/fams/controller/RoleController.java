package xyz.kakusei.fams.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xyz.kakusei.fams.entity.Role;
import xyz.kakusei.fams.query.GeneralQueryObject;
import xyz.kakusei.fams.service.IPermissionService;
import xyz.kakusei.fams.service.IRoleService;
import xyz.kakusei.fams.util.RequiredPermission;
import xyz.kakusei.fams.util.SystemSetting;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private SystemSetting setting;

    @RequiredPermission({"Query Role","role:query"})
    @GetMapping("/tables")
    public String tables(Model model) {
        PageHelper.startPage(1, setting.getPageSize());
        model.addAttribute("pageResult", new PageInfo<Role>(roleService.queryAll()));
        return "/role/tables";
    }
    @RequiredPermission({"Query Role","role:query"})
    @PostMapping("/query")
    @ResponseBody
    public PageInfo<Role> query(GeneralQueryObject roleQueryObject) {
        PageHelper.startPage(roleQueryObject.getPageNum(), roleQueryObject.getPageSize());
        PageInfo<Role> result = new PageInfo<Role>(roleService.queryByCriteria(roleQueryObject));
        return result;
    }
    @RequiredPermission({"Role Detail","role:detail"})
    @GetMapping("/{id}")
    public String role(Model model, @PathVariable("id") Byte id) {
        model.addAttribute("role", roleService.queryByRoleId(id));
        model.addAttribute("permissions", permissionService.queryAll());
        return "/role/form";
    }
    @RequiredPermission({"Delete Role","role:delete"})
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Byte id) {
        roleService.deleteByRoleId(id);
        return "redirect:/role/tables";
    }
    @RequiredPermission({"Modify Role","role:modify"})
    @GetMapping("/new")
    public String newRole(Model model) {
        model.addAttribute("role", new Role());
        model.addAttribute("permissions", permissionService.queryAll());
        return "/role/form";
    }
    @RequiredPermission({"Modify Role","role:modify"})
    @PostMapping("/new")
    public String saveOrUpdate(Role role, Byte[] permissionIds) {
        roleService.saveOrUpdate(role, permissionIds);
        return "redirect:/role/tables";
    }
}
