package xyz.kakusei.fams.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.kakusei.fams.entity.State;
import xyz.kakusei.fams.mapper.IEmployeeStateMapper;
import xyz.kakusei.fams.service.IEmployeeStateService;

import java.util.List;

@Service
public class EmployeeStateServiceImpl implements IEmployeeStateService {

    @Autowired
    private IEmployeeStateMapper employeeStateMapper;

    @Override
    public List<State> queryAll() {
        return employeeStateMapper.queryAll();
    }

    @Override
    public State queryById(Byte id) {
        return employeeStateMapper.queryById(id);
    }

    @Override
    public void deleteById(Byte id) {
        employeeStateMapper.deleteById(id);
    }

    @Override
    public void saveOrUpdate(State employeeState) {
        if (employeeState.getId() == null) {
            employeeStateMapper.insert(employeeState);
        }
        else {
            employeeStateMapper.update(employeeState);
        }
    }
}
