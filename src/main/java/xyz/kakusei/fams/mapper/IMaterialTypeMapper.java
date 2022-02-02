package xyz.kakusei.fams.mapper;

import xyz.kakusei.fams.entity.MaterialType;

import java.util.List;

public interface IMaterialTypeMapper {
    MaterialType queryById(Byte id);
    List<MaterialType> queryAll();
    void insert(MaterialType materialType);
    void update(MaterialType materialType);
    void deleteById(Byte id);
}
