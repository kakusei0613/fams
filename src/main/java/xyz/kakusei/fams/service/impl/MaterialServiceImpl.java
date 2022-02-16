package xyz.kakusei.fams.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.kakusei.fams.entity.Material;
import xyz.kakusei.fams.mapper.IMaterialMapper;
import xyz.kakusei.fams.query.MaterialQueryObject;
import xyz.kakusei.fams.service.IMaterialService;

import java.util.List;

@Service
public class MaterialServiceImpl implements IMaterialService {

    @Autowired
    private IMaterialMapper materialMapper;

    @Override
    public List<Material> queryAll() {
        return materialMapper.queryAll();
    }

    @Override
    public List<Material> queryByCriteria(MaterialQueryObject materialQueryObject) {
        return materialMapper.queryByCriteria(materialQueryObject);
    }

    @Override
    public Material queryById(Long id) {
        return materialMapper.queryById(id);
    }

    @Override
    public void saveOrUpdate(Material material) {
        if (material.getId() == null) {
            materialMapper.insert(material);
        }
        else {
            materialMapper.update(material);
        }
    }

    @Override
    public void deleteById(Long id) {
        materialMapper.deleteById(id);
    }
}
