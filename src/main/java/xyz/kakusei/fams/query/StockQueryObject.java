package xyz.kakusei.fams.query;

import lombok.Data;

@Data
public class StockQueryObject {
    private Byte typeId;
    private Integer warehouseId;
    private String materialName;
    private String parameter;
}
