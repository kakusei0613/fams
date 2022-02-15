package xyz.kakusei.fams.entity;

import lombok.Data;

import java.lang.reflect.Field;
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

//    public Boolean modify(Order obj) {
//        if (obj == null)
//            throw new NullPointerException("Object was null.");
//        if (obj.getId() != this.getId())
//            throw new IllegalArgumentException("Id is not equal.");
//        if (this.customer.getId() != obj.getCustomer().getId() || !this.ddl.equals(obj.getDdl()) || this.state.getId() != obj.getState().getId() || !this.comments.equals(obj.getComments()) ) {
//            return true;
//        }
//        return false;
//    }
}
