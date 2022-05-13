package xyz.kakusei.fams.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import xyz.kakusei.fams.entity.*;
import xyz.kakusei.fams.mapper.IMaterialApplicationMapper;
import xyz.kakusei.fams.mapper.IStateMapper;
import xyz.kakusei.fams.mapper.IStockMapper;
import xyz.kakusei.fams.query.MaterialApplicationQueryObject;
import xyz.kakusei.fams.service.IEmployeeService;
import xyz.kakusei.fams.service.IMaterialApplicationService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class MaterialApplicationServiceImpl implements IMaterialApplicationService {

    @Autowired
    private IMaterialApplicationMapper materialApplicationMapper;

    @Autowired
    private IStateMapper stateMapper;

    @Autowired
    private IStockMapper stockMapper;

    @Autowired
    private IEmployeeService employeeService;

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public List<MaterialApplication> queryAll() {
        return materialApplicationMapper.queryAll();
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public MaterialApplication queryById(Long id) {
        return materialApplicationMapper.queryById(id);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public List<MaterialApplication> queryByCriteria(MaterialApplicationQueryObject materialApplicationQueryObject) {
        return materialApplicationMapper.queryByCriteria(materialApplicationQueryObject);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void deleteById(Long id) {
        materialApplicationMapper.deleteById(id);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void saveOrUpdate(MaterialApplication materialApplication) {
        if (materialApplication.getId() == null) {
            materialApplicationMapper.insert(materialApplication);
        }
        else {
            materialApplicationMapper.update(materialApplication);
        }
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void newRecord(Long orderId, Long[] stockIds, Integer[] quantity) {
        if (stockIds.length != quantity.length) {
            throw new RuntimeException("Inconsistent parameter length.");
        }
        Date date = new Date(System.currentTimeMillis());
        Integer length = stockIds.length;
        Employee user = employeeService.getCurrentUserData();
        MaterialApplication record = new MaterialApplication();
        record.setOrder(new Order());
        record.getOrder().setId(orderId);
        record.setEmployee(user);
        record.setState(new State());
        record.getState().setId(Byte.parseByte("2"));
        record.setApplicationTime(simpleDateFormat.format(date));
        record.setOperator(user);
        record.setOperationTime(simpleDateFormat.format(date));
        record.setStock(new Stock());
        for (int i = 0; i < length; i++) {
            record.getStock().setId(stockIds[i]);
            record.setQuantity(quantity[i]);
            materialApplicationMapper.insert(record);
        }
    }

    @Override
    public List<State> queryAllState() {
        return stateMapper.queryAll(IStateMapper.MATERIAL_APPLICATION_STATE_TABLE);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Boolean setPassState(Long id, String comments) {
        try {
            MaterialApplication record = materialApplicationMapper.queryById(id);
            Stock stock = record.getStock();
            Integer value = stock.getQuantity() - record.getQuantity();
            if (value < 0) {
                return false;
            }
            stock.setQuantity(value);
            stockMapper.update(stock);
            record.getState().setId(Byte.parseByte("1"));
            record.setComments(comments);
            materialApplicationMapper.update(record);
            return true;
        }
        catch (Exception e) {
            return false;
        }

    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Boolean setRefuseState(Long id, String comments) {
        try {
            MaterialApplication record = materialApplicationMapper.queryById(id);
            record.getState().setId(Byte.parseByte("3"));
            record.setComments(comments);
            materialApplicationMapper.update(record);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
