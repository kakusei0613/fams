package xyz.kakusei.fams.service;

import xyz.kakusei.fams.entity.MaterialApplication;
import xyz.kakusei.fams.entity.State;
import xyz.kakusei.fams.query.MaterialApplicationQueryObject;

import java.util.List;

public interface IMaterialApplicationService {
    List<MaterialApplication> queryAll();
    MaterialApplication queryById(Long id);
    List<MaterialApplication> queryByCriteria(MaterialApplicationQueryObject materialApplicationQueryObject);
    void deleteById(Long id);
    void saveOrUpdate(MaterialApplication materialApplication);
    void newRecord(Long orderId, Long[] stockIds, Integer[] quantity);
    List<State> queryAllState();
    Boolean setPassState(Long id, String comments);
    Boolean setRefuseState(Long id, String comments);
}
