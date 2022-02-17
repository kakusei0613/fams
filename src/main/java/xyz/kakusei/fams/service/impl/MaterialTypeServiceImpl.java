package xyz.kakusei.fams.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.kakusei.fams.entity.MaterialType;
import xyz.kakusei.fams.mapper.IMaterialTypeMapper;
import xyz.kakusei.fams.service.IMaterialTypeService;

import java.util.List;

@Service
public class MaterialTypeServiceImpl implements IMaterialTypeService {

    @Autowired
    private IMaterialTypeMapper materialTypeMapper;

    @Override
    public List<MaterialType> queryAll() {
        return materialTypeMapper.queryAll();
    }

    @Override
    public MaterialType queryById(Integer id) {
        return materialTypeMapper.queryById(id);
    }

    @Override
    public void saveOrUpdate(MaterialType materialType) {
        if (materialType.getId() == null) {
            materialTypeMapper.insert(materialType);
        }
        else {
            materialTypeMapper.update(materialType);
        }
    }

    @Override
    public void deleteById(Integer id) {
        materialTypeMapper.deleteById(id);
    }
}
