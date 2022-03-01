package xyz.kakusei.fams.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.kakusei.fams.entity.Customer;
import xyz.kakusei.fams.mapper.ICustomerMapper;
import xyz.kakusei.fams.query.GeneralQueryObject;
import xyz.kakusei.fams.service.ICustomerService;

import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private ICustomerMapper customerMapper;

    @Override
    public List<Customer> queryAll() {
        return customerMapper.queryAll();
    }

    @Override
    public Customer queryById(Integer id) {
        return customerMapper.queryById(id);
    }

    @Override
    public void saveOrUpdate(Customer customer) {
        if (customer.getId() == null) {
            customerMapper.insert(customer);
        }
        else {
            customerMapper.update(customer);
        }
    }

    @Override
    public void deleteById(Integer id) {
        customerMapper.deleteById(id);
    }

    @Override
    public List<Customer> queryByCriteria(GeneralQueryObject customerQueryObject) {
        return customerMapper.queryByCriteria(customerQueryObject);
    }
}
