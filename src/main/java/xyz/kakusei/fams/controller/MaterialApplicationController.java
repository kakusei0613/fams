package xyz.kakusei.fams.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xyz.kakusei.fams.entity.MaterialApplication;
import xyz.kakusei.fams.query.MaterialApplicationQueryObject;
import xyz.kakusei.fams.service.IMaterialApplicationService;

@Controller
@RequestMapping("/material/application")
public class MaterialApplicationController {

    @Autowired
    private IMaterialApplicationService materialApplicationService;

    @GetMapping("/tables")
    public String tables(Model model) {
        PageHelper.startPage(1, 15);
        model.addAttribute("pageResult", new PageInfo<MaterialApplication>());
        return "/material/application/tables";
    }
    @GetMapping("/{id}")
    public String queryById(Model model, @PathVariable("id") Long id) {
        model.addAttribute("materialApplication", materialApplicationService.queryById(id));
        return "/material/application/form";
    }
    @GetMapping("/new")
    public String newMaterialApplication(Model model) {
        model.addAttribute("materialApplication", new MaterialApplication());
        return "/material/application/form";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        materialApplicationService.deleteById(id);
        return "redirect:/material/application/tables";
    }
    @PostMapping("/query")
    @ResponseBody
    public PageInfo<MaterialApplication> query(MaterialApplicationQueryObject materialApplicationQueryObject) {
        PageHelper.startPage(materialApplicationQueryObject.getPageNum(), materialApplicationQueryObject.getPageSize());
        PageInfo<MaterialApplication> result = new PageInfo<MaterialApplication>(materialApplicationService.queryByCriteria(materialApplicationQueryObject));
        return result;
    }
}
