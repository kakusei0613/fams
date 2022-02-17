package xyz.kakusei.fams.service;

import xyz.kakusei.fams.entity.MaterialType;

import java.util.List;

public interface IMaterialTypeService {
    List<MaterialType> queryAll();
    MaterialType queryById(Integer id);
    void saveOrUpdate(MaterialType materialType);
    void deleteById(Integer id);
}
