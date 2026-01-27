package com.springboot.springboot2.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "欠费信息")
public class UnpaidOwner {
    @Schema(description = "欠费人员信息")
    private User user;
    @Schema(description = "欠费账单信息")
    private Amount amount;
    @Schema(description = "欠费房间信息")
    private Room room;
}
