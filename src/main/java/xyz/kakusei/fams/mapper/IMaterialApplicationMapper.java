package xyz.kakusei.fams.mapper;

import xyz.kakusei.fams.entity.MaterialApplication;
import xyz.kakusei.fams.query.MaterialApplicationQueryObject;

import java.util.List;

public interface IMaterialApplicationMapper {
    List<MaterialApplication> queryAll();
    MaterialApplication queryById(Long id);
    List<MaterialApplication> queryByCriteria(MaterialApplicationQueryObject materialApplicationQueryObject);
    void insert(MaterialApplication materialApplication);
    void update(MaterialApplication materialApplication);
    void deleteById(Long id);
}
