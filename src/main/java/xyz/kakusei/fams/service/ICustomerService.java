package xyz.kakusei.fams.service;

import xyz.kakusei.fams.entity.Customer;
import xyz.kakusei.fams.query.GeneralQueryObject;

import java.util.List;

public interface ICustomerService {
    List<Customer> queryAll();
    Customer queryById(Integer id);
    void saveOrUpdate(Customer department);
    void deleteById(Integer id);
    List<Customer> queryByCriteria(GeneralQueryObject customerQueryObject);
}
