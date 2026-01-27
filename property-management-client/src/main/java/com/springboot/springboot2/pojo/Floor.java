package com.springboot.springboot2.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "楼层信息，包括每层楼多少间房及楼层序号")
public class Floor implements Cloneable {

    @Schema(description = "楼层信息标识")
    private Integer floorId;

    @Schema(description = "楼层所属建筑的标识")
    private Integer belongBuilding;

    @Schema(description = "第几层")
    private Short floorNumber;

    @Schema(description = "该楼层房间数量")
    private Short roomNumber;

    @Schema(description = "该楼层所属楼栋")
    private String buildingName; // 改为 String 类型，方便处理

    @Override
    public Floor clone() {
        try {
            return (Floor) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("克隆失败", e);
        }
    }
}
