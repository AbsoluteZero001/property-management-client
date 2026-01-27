package com.springboot.springboot2.controller;

import com.springboot.springboot2.constant.FreightBasisData.FreightBasisData;
import com.springboot.springboot2.pojo.FreightBasis;
import com.springboot.springboot2.pojo.ResponsePojo;
import com.springboot.springboot2.service.FreightBasisService;
import com.springboot.springboot2.utils.StringUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/freightBasis")
@Tag(name = "费率相关API", description = "费率相关操作接口")
public class FreightBasisController {

    private final FreightBasisService freightBasisService;
    private final FreightBasisData freightBasisData;

    // ✅ 构造函数注入（推荐方式）
    public FreightBasisController(FreightBasisService freightBasisService, FreightBasisData freightBasisData) {
        this.freightBasisService = freightBasisService;
        this.freightBasisData = freightBasisData;
    }

    // ✅ 示例接口
    @GetMapping("/list")
    @Operation(summary = "获取费率列表", description = "返回所有费率基础信息")
    public String getFreightList() {
        return "费率列表";
    }

    // ✅ 查询费用说明
    @PostMapping("/queryAll")
    @Operation(summary = "查询费用说明", description = "返回水费、电费、物业费基础数据")
    public ResponsePojo<FreightBasisData> queryAll() {
        return ResponsePojo.success(freightBasisData);
    }

    // ✅ 修改费用说明接口
    @PutMapping("/updateById")
    @Operation(summary = "修改费用说明，可以修改单价、说明或名称")
    public ResponsePojo<Integer> updateById(
            @Parameter(description = "单价，两位小数") @RequestParam(required = false) String basisPrice,
            @Parameter(description = "费用介绍") @RequestParam(required = false) String intro,
            @Parameter(description = "费用名称") @RequestParam(required = false) String basisName,
            @Parameter(description = "费用id") @RequestParam Integer freightBasisId,
            HttpServletResponse res
    ) {
        FreightBasis fh = new FreightBasis();
        fh.setFreightBasisId(freightBasisId);
        fh.setIntro(intro);
        fh.setBasisName(basisName);

        try {
            if (basisPrice != null && !basisPrice.isEmpty()) {
                fh.setPrice(StringUtil.getNumberFromString(basisPrice));
            }
        } catch (Exception e) {
            try {
                res.sendError(403, e.getMessage());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            return ResponsePojo.fail(2, "单价格式错误");
        }

        int result = freightBasisService.updateById(fh);
        switch (result) {
            case 1:
                freightBasisData.flushData();
                return ResponsePojo.success(result);
            case 0:
                return ResponsePojo.fail(0, "该id没有找到对应信息");
            default:
                return ResponsePojo.fail(2, "参数有误，修改失败");
        }
    }
}
