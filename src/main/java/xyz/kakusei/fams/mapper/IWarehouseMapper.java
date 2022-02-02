package xyz.kakusei.fams.mapper;

import xyz.kakusei.fams.entity.Warehouse;

import java.util.List;

public interface IWarehouseMapper {
    List<Warehouse> queryAll();
    Warehouse queryById();
    void insert(Warehouse warehouse);
    void update(Warehouse warehouse);
    void deleteById(Integer id);
}
