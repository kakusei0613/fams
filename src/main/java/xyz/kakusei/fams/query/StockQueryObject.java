package xyz.kakusei.fams.query;

import lombok.Data;

@Data
public class StockQueryObject extends BasicQueryObject {
    private Byte typeId;
    private Byte warehouseId;
    private String keyword;
}
