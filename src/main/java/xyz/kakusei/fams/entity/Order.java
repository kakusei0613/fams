package xyz.kakusei.fams.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Order {
    private Long id;
    private Customer customer;
    private BigDecimal price;
    private String ddl;
    private String createTime;
    private Employee creator;
    private OrderState state;
    private String processTime;
}
