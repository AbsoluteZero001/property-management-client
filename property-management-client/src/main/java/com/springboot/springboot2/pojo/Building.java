package com.springboot.springboot2.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

/**
 * 楼栋信息实体类
 */
@Data
@Schema(description = "楼栋信息实体")
public class Building implements Cloneable {

    @Schema(description = "楼栋主键ID，唯一标识")
    private Integer buildingId;

    @Schema(description = "楼栋名称")
    private String buildingName;

    @Schema(description = "楼栋编码")
    private String buildingCode;

    @Schema(description = "楼栋状态：0=未使用，1=已启用")
    private Short buildingStatus;

    @Schema(description = "楼层数")
    private Short buildingFloors;

    @Schema(description = "竣工日期")
    private LocalDate completionDate;

    @Schema(description = "设计使用年限")
    private Short designLife;

    @Schema(description = "产权类型")
    private Short ownership;

    /**
     * 克隆方法，生成深拷贝
     */
    public Building cloneBuilding() {
        Building clone = new Building();
        clone.setBuildingId(this.buildingId);
        clone.setBuildingName(this.buildingName);
        clone.setBuildingCode(this.buildingCode);
        clone.setBuildingStatus(this.buildingStatus);
        clone.setBuildingFloors(this.buildingFloors);
        clone.setCompletionDate(this.completionDate);
        clone.setDesignLife(this.designLife);
        clone.setOwnership(this.ownership);
        return clone;
    }
}
