package com.springboot.springboot2.controller;

import com.springboot.springboot2.pojo.PageResult;
import com.springboot.springboot2.pojo.PayInfo;
import com.springboot.springboot2.pojo.ResponsePojo;
import com.springboot.springboot2.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/pay")
@Tag(name = "支付信息API", description = "用于查询支付信息的接口")
public class PayController {

    private final PayService payService;

    // ✅ 推荐使用构造函数注入（更安全、可测试）
    public PayController(PayService payService) {
        this.payService = payService;
    }

    @GetMapping("/payInfos")
    @Operation(summary = "支付信息分页查询", description = "支持按时间段、用户信息、楼层和建筑进行筛选")
    @Parameters({
            @Parameter(name = "current", description = "当前页数", example = "1"),
            @Parameter(name = "size", description = "每页大小", example = "10"),
            @Parameter(name = "start", description = "开始日期，格式：yyyy-MM-dd", example = "2025-01-01"),
            @Parameter(name = "end", description = "结束日期，格式：yyyy-MM-dd", example = "2025-12-31"),
            @Parameter(name = "username", description = "用户姓名", example = "张三"),
            @Parameter(name = "idCard", description = "身份证号", example = "510123199901010011"),
            @Parameter(name = "floorId", description = "楼层ID", example = "3"),
            @Parameter(name = "buildingId", description = "建筑ID", example = "12")
    })
    public ResponsePojo<PageResult<PayInfo>> getPaysInfo(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String idCard,
            @RequestParam(required = false) Integer floorId,
            @RequestParam(required = false) Integer buildingId
    ) {
        if (start != null && end == null) {
            end = LocalDate.now();
        }

        PageResult<PayInfo> page = payService.pageOfPayInfo(
                current, size, start, end, username, idCard, floorId, buildingId
        );

        return ResponsePojo.success(page);
    }
}
