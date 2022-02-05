package xyz.kakusei.fams.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Order {
    private Long id;
    private Customer customer;
    private BigDecimal price;
    private String ddl;
    private String createTime;
    private Employee creator;
    private State state;
    private String processTime;
    private String comments;
    private List<Employee> staffs;
}
