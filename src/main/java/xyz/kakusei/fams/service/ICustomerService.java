package xyz.kakusei.fams.service;

import xyz.kakusei.fams.entity.Customer;
import xyz.kakusei.fams.entity.Department;
import xyz.kakusei.fams.query.CustomerQueryObject;

import java.util.List;

public interface ICustomerService {
    List<Customer> queryAll();
    Customer queryById(Integer id);
    void saveOrUpdate(Customer department);
    void deleteById(Integer id);
    List<Customer> queryByCriteria(CustomerQueryObject customerQueryObject);
}
