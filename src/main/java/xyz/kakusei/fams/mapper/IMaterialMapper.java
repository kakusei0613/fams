package xyz.kakusei.fams.mapper;

import org.apache.ibatis.annotations.Param;
import xyz.kakusei.fams.entity.Material;

import java.util.List;

public interface IMaterialMapper {
    List<Material> queryAll();
    Material queryById(@Param("id") Long id);
    List<Material> queryByTypeId(@Param("type") Integer id);
    void insert(Material material);
    void update(Material material);
    void deleteById(@Param("id") Long id);
}
