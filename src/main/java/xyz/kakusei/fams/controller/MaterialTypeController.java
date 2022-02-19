package xyz.kakusei.fams.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xyz.kakusei.fams.entity.MaterialType;
import xyz.kakusei.fams.query.BasicQueryObject;
import xyz.kakusei.fams.service.IMaterialTypeService;
import xyz.kakusei.fams.util.RequiredPermission;

@Controller
@RequestMapping("/material/type")
public class MaterialTypeController {

    @Autowired
    private IMaterialTypeService materialTypeService;

    @RequiredPermission({"Query Material Type","material:query"})
    @GetMapping("/tables")
    public String tables(Model model) {
        PageHelper.startPage(1,15);
        model.addAttribute("pageResult", new PageInfo<MaterialType>(materialTypeService.queryAll()));
        return "/material/type/tables";
    }
    @RequiredPermission({"Query Material Type","material:query"})
    @PostMapping("/query")
    @ResponseBody
    public PageInfo<MaterialType> query(BasicQueryObject basicQueryObject) {
        PageHelper.startPage(basicQueryObject.getPageNum(), basicQueryObject.getPageSize());
        PageInfo<MaterialType> result = new PageInfo<MaterialType>(materialTypeService.queryAll());
        return result;
    }
    @RequiredPermission({"Material Type Detail","materialType:detail"})
    @GetMapping("/{id}")
    public String queryById(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("type", materialTypeService.queryById(id));
        return "/material/type/form";
    }
    @RequiredPermission({"Delete Material Type","material:delete"})
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        materialTypeService.deleteById(id);
        return "redirect:/material/type/tables";
    }
    @RequiredPermission({"Modify Material Type","material:modify"})
    @GetMapping("/new")
    public String newMaterialType(Model model) {
        model.addAttribute("type", new MaterialType());
        return "/material/type/form";
    }
    @RequiredPermission({"Modify Material Type","material:modify"})
    @PostMapping("/new")
    public String saveOrUpdate(MaterialType materialType) {
        materialTypeService.saveOrUpdate(materialType);
        return "redirect:/material/type/tables";
    }
}
