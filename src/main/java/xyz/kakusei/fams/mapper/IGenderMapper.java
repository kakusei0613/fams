package xyz.kakusei.fams.mapper;

import xyz.kakusei.fams.entity.Gender;
import xyz.kakusei.fams.entity.State;

import java.util.List;

public interface IGenderMapper {
    List<Gender> queryAll();
    State queryById(Byte id);
    void insert(Gender gender);
    void update(Gender gender);
    void deleteById(Byte id);
}
