package xyz.kakusei.fams.mapper;

import xyz.kakusei.fams.entity.State;

import java.util.List;

public interface IMaterialApplicationStateMapper {
    List<State> queryAll();
    State queryById(Byte id);
    void insert(State materialApplicationState);
    void update(State materialApplicationState);
    void deleteById(Byte id);
}
