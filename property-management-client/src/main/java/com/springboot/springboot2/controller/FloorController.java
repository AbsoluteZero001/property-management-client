package com.springboot.springboot2.controller;

import com.springboot.springboot2.pojo.Floor;
import com.springboot.springboot2.pojo.ResponsePojo;
import com.springboot.springboot2.service.FloorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Tag(name = "楼层相关API")
@RequestMapping("/floor")
@RestController
public class FloorController {

    @Autowired
    private FloorService floorService;

    @GetMapping("/floorsOfBuilding/{buildingId}")
    @Operation(summary = "根据楼栋ID查询楼层信息")
    public ResponsePojo<List<Floor>> floorsOfBuilding(
            @Parameter(description = "建筑ID", required = true)
            @PathVariable("buildingId") Integer buildingId) {

        List<Floor> list = floorService.floorsOfBuilding(buildingId);
        if (list != null && !list.isEmpty()) {
            return ResponsePojo.success(list);
        }
        return ResponsePojo.fail(list, "buildingId 查询不到对应房间");
    }
}
