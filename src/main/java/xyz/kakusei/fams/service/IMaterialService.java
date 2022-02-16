package xyz.kakusei.fams.service;

import xyz.kakusei.fams.entity.Material;
import xyz.kakusei.fams.query.MaterialQueryObject;

import java.util.List;

public interface IMaterialService {
    List<Material> queryAll();
    List<Material> queryByCriteria(MaterialQueryObject materialQueryObject);
    Material queryById(Long id);
    void saveOrUpdate(Material material);
    void deleteById(Long id);
}
