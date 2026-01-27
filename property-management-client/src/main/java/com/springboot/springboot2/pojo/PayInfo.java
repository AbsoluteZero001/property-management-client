package com.springboot.springboot2.pojo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "支付信息")
public class PayInfo {
    @Schema(description = "支付人员信息")
    private User user;
    @Schema(description = "支付人员所在建筑")
    private Building building;
    @Schema(description = "支付人员所住房间")
    private Room room;
    @Schema(description = "支付金额相关")
    private Pay pay;
}
