package xyz.kakusei.fams.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.kakusei.fams.entity.MaterialApplication;
import xyz.kakusei.fams.mapper.IMaterialApplicationMapper;
import xyz.kakusei.fams.query.MaterialApplicationQueryObject;
import xyz.kakusei.fams.service.IMaterialApplicationService;

import java.util.List;

@Service
public class MaterialApplicationServiceImpl implements IMaterialApplicationService {

    @Autowired
    private IMaterialApplicationMapper materialApplicationMapper;

    @Override
    public List<MaterialApplication> queryAll() {
        return materialApplicationMapper.queryAll();
    }

    @Override
    public MaterialApplication queryById(Long id) {
        return materialApplicationMapper.queryById(id);
    }

    @Override
    public List<MaterialApplication> queryByCriteria(MaterialApplicationQueryObject materialApplicationQueryObject) {
        return materialApplicationMapper.queryByCriteria(materialApplicationQueryObject);
    }

    @Override
    public void deleteById(Long id) {
        materialApplicationMapper.deleteById(id);
    }

    @Override
    public void saveOrUpdate(MaterialApplication materialApplication) {
        if (materialApplication.getId() == null) {
            materialApplicationMapper.insert(materialApplication);
        }
        else {
            materialApplicationMapper.update(materialApplication);
        }
    }
}
