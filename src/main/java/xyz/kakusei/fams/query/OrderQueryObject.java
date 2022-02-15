package xyz.kakusei.fams.query;

import lombok.Data;

@Data
public class OrderQueryObject extends BasicQueryObject{
    private Integer customerId;
    private Long creatorId;
    private Byte stateId;
    private String keyword;
}
