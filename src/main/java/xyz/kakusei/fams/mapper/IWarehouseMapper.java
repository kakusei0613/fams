package xyz.kakusei.fams.mapper;

import xyz.kakusei.fams.entity.Warehouse;
import xyz.kakusei.fams.query.WarehouseQueryObject;

import java.util.List;

public interface IWarehouseMapper {
    List<Warehouse> queryAll();
    Warehouse queryById(Byte id);
    List<Warehouse> queryByCriteria(WarehouseQueryObject warehouseQueryObject);
    void insert(Warehouse warehouse);
    void update(Warehouse warehouse);
    void deleteById(Byte id);
}
