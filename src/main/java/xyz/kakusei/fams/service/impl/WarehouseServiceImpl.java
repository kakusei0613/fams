package xyz.kakusei.fams.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.kakusei.fams.entity.Warehouse;
import xyz.kakusei.fams.mapper.IWarehouseMapper;
import xyz.kakusei.fams.query.GeneralQueryObject;
import xyz.kakusei.fams.service.IWarehouseService;

import java.util.List;

@Service
public class WarehouseServiceImpl implements IWarehouseService {

    @Autowired
    private IWarehouseMapper warehouseMapper;

    @Override
    public List<Warehouse> queryAll() {
        return warehouseMapper.queryAll();
    }

    @Override
    public Warehouse queryById(Byte id) {
        return warehouseMapper.queryById(id);
    }

    @Override
    public void saveOrUpdate(Warehouse warehouse) {
        if (warehouse.getId() == null) {
            warehouseMapper.insert(warehouse);
        }
        else {
            warehouseMapper.update(warehouse);
        }
    }

    @Override
    public void deleteById(Byte id) {
        warehouseMapper.deleteById(id);
    }

    @Override
    public List<Warehouse> queryByCriteria(GeneralQueryObject warehouseQueryObject) {
        return warehouseMapper.queryByCriteria(warehouseQueryObject);
    }
}
