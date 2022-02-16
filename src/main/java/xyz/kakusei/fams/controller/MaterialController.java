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

@Controller
@RequestMapping("/material")
public class MaterialController {

    @Autowired
    private IMaterialService materialService;

    @Autowired
    private IMaterialTypeService materialTypeService;

    @GetMapping("/tables")
    public String tables(Model model) {
        PageHelper.startPage(1,15);
        model.addAttribute("pageResult", new PageInfo<Material>(materialService.queryAll()));
        model.addAttribute("types", materialTypeService.queryAll());
        return "/material/tables";
    }

    @GetMapping("/{id}")
    public String queryById(Model model, @PathVariable("id") Long id) {
        model.addAttribute("material", materialService.queryById(id));
        model.addAttribute("types", materialTypeService.queryAll());
        return "/material/form";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        materialService.deleteById(id);
        return "redirect:/material/tables";
    }
    @GetMapping("/new")
    public String newMaterial(Model model) {
        model.addAttribute("material", new Material());
        model.addAttribute("types", materialTypeService.queryAll());
        return "/material/form";
    }
    @PostMapping("/new")
    public String saveOrUpdate(Material material) {
        materialService.saveOrUpdate(material);
        return "redirect:/material/tables";
    }
    @PostMapping("/query")
    @ResponseBody
    public PageInfo<Material> query(MaterialQueryObject materialQueryObject) {
        PageHelper.startPage(materialQueryObject.getPageNum(), materialQueryObject.getPageSize());
        PageInfo<Material> result = new PageInfo<Material>(materialService.queryByCriteria(materialQueryObject));
        return result;
    }
}
