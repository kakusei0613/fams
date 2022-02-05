package xyz.kakusei.fams.mapper;

import xyz.kakusei.fams.entity.MaterialApplication;

import java.util.List;

public interface IMaterialApplicationMapper {
    List<MaterialApplication> queryAll();
    MaterialApplication queryById(Long id);
    void insert(MaterialApplication materialApplication);
    void update(MaterialApplication materialApplication);
    void deleteById(Long id);
}
