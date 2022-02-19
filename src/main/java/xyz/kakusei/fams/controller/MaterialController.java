package xyz.kakusei.fams.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xyz.kakusei.fams.entity.Material;
import xyz.kakusei.fams.query.MaterialQueryObject;
import xyz.kakusei.fams.service.IMaterialService;
import xyz.kakusei.fams.service.IMaterialTypeService;
import xyz.kakusei.fams.util.RequiredPermission;

@Controller
@RequestMapping("/material")
public class MaterialController {

    @Autowired
    private IMaterialService materialService;

    @Autowired
    private IMaterialTypeService materialTypeService;

    @RequiredPermission({"Query Material","material:query"})
    @GetMapping("/tables")
    public String tables(Model model) {
        PageHelper.startPage(1,15);
        model.addAttribute("pageResult", new PageInfo<Material>(materialService.queryAll()));
        model.addAttribute("types", materialTypeService.queryAll());
        return "/material/tables";
    }
    @RequiredPermission({"Material Detail","material:detail"})
    @GetMapping("/{id}")
    public String queryById(Model model, @PathVariable("id") Long id) {
        model.addAttribute("material", materialService.queryById(id));
        model.addAttribute("types", materialTypeService.queryAll());
        return "/material/form";
    }
    @RequiredPermission({"Query Material","material:query"})
    @PostMapping("/query")
    @ResponseBody
    public PageInfo<Material> query(MaterialQueryObject materialQueryObject) {
        PageHelper.startPage(materialQueryObject.getPageNum(), materialQueryObject.getPageSize());
        PageInfo<Material> result = new PageInfo<Material>(materialService.queryByCriteria(materialQueryObject));
        return result;
    }
    @RequiredPermission({"Delete Material","material:delete"})
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        materialService.deleteById(id);
        return "redirect:/material/tables";
    }
    @RequiredPermission({"Modify Material","material:modify"})
    @GetMapping("/new")
    public String newMaterial(Model model) {
        model.addAttribute("material", new Material());
        model.addAttribute("types", materialTypeService.queryAll());
        return "/material/form";
    }
    @RequiredPermission({"Modify Material","material:modify"})
    @PostMapping("/new")
    public String saveOrUpdate(Material material) {
        materialService.saveOrUpdate(material);
        return "redirect:/material/tables";
    }
}
