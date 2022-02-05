package xyz.kakusei.fams.mapper;

import xyz.kakusei.fams.entity.State;

import java.util.List;

public interface IOrderStateMapper {
    List<State> queryAll();
    State queryById(Byte id);
    void insert(State orderState);
    void update(State orderState);
    void deleteById(Byte id);
}
