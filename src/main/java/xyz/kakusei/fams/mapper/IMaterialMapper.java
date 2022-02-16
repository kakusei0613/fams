package xyz.kakusei.fams.mapper;

import org.apache.ibatis.annotations.Param;
import xyz.kakusei.fams.entity.Material;
import xyz.kakusei.fams.query.MaterialQueryObject;

import java.util.List;

public interface IMaterialMapper {
    List<Material> queryAll();
    List<Material> queryByCriteria(MaterialQueryObject materialQueryObject);
    Material queryById(@Param("id") Long id);
    List<Material> queryByTypeId(@Param("type") Integer id);
    void insert(Material material);
    void update(Material material);
    void deleteById(@Param("id") Long id);
}
