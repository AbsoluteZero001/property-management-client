package com.springboot.springboot2.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "房屋信息。与楼层相关")
public class Room {
    @Schema(description = "房间的唯一标识")
    private String roomId;

    @Schema(description = "房屋所属楼层的标识ID")
    private String roomFloorId;

    @Schema(description = "建筑面积")
    private String builtUpArea;

    @Schema(description = "房屋状态,1.自住；2.出租；3.未居住；4.未出售；5.公共空间")
    private String roomStatus;

    @Schema(description = "房屋编号,1-1-1表示1号楼1层1号房")
    private String roomCode;

    // 新增字段
    @Schema(description = "楼层序号")
    private String floorNumber;

    @Schema(description = "建筑名称")
    private String buildingName;
}
