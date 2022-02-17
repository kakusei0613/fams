package xyz.kakusei.fams.service;

import xyz.kakusei.fams.entity.Warehouse;
import xyz.kakusei.fams.query.WarehouseQueryObject;

import java.util.List;

public interface IWarehouseService {
    List<Warehouse> queryAll();
    Warehouse queryById(Byte id);
    void saveOrUpdate(Warehouse warehouse);
    void deleteById(Byte id);
    List<Warehouse> queryByCriteria(WarehouseQueryObject warehouseQueryObject);
}
