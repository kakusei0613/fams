package xyz.kakusei.fams.mapper;


import xyz.kakusei.fams.entity.Customer;
import xyz.kakusei.fams.query.GeneralQueryObject;

import java.util.List;

public interface ICustomerMapper {
    List<Customer> queryAll();
    Customer queryById(Integer id);
    void update(Customer customer);
    void insert(Customer customer);
    void deleteById(Integer id);
    List<Customer> queryByCriteria(GeneralQueryObject customerQueryObject);
}
