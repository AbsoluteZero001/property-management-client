package com.springboot.springboot2.controller;

import com.springboot.springboot2.pojo.Building;
import com.springboot.springboot2.pojo.Floor;
import com.springboot.springboot2.pojo.PageResult;
import com.springboot.springboot2.pojo.ResponsePojo;
import com.springboot.springboot2.service.BuildingService;
import com.springboot.springboot2.service.FloorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "楼栋相关API")
@RequestMapping("/building")
@RestController
public class BuildingController {

    private final BuildingService buildingService;

    private final FloorService floorService;

    public BuildingController(BuildingService buildingService, FloorService floorService) {
        this.buildingService = buildingService;
        this.floorService = floorService;
    }

    /**
     * 查询在用楼栋列表
     */
    @GetMapping("/buildingOfUsed")
    @Operation(summary = "查询在用楼栋列表")
    public ResponsePojo<List<com.springboot.springboot2.pojo.Building>> buildingOfUsed() {
        return ResponsePojo.success(buildingService.buildingsOfUsed());
    }

    /**
     * 新增单个楼栋
     */
    @PostMapping("/addBuilding")
    @Operation(summary = "新增单个楼栋")
    public ResponsePojo<Integer> addBuilding(@RequestBody com.springboot.springboot2.pojo.Building building) {
        int result = -1;
        try {
            result = buildingService.insertBuilding(building);
            return ResponsePojo.success(result);
        } catch (DuplicateKeyException e) {
            return ResponsePojo.fail(result, "楼栋编码已存在");
        }
    }

    /**
     * 分页查询楼栋信息
     */
    @GetMapping(value = "/page/building", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "查询楼栋分页信息",
            description = "分页返回楼栋列表信息"
    )

    public ResponsePojo<PageResult<com.springboot.springboot2.pojo.Building>> pageOfBuilding(
            @Parameter(description = "当前页数", example = "1")
            @RequestParam(defaultValue = "1") Integer current,

            @Parameter(description = "每页行数", example = "10")
            @RequestParam(defaultValue = "10") Integer size
    ) {
        PageResult<com.springboot.springboot2.pojo.Building> result = buildingService.pageOfBuilding(current, size);
        return ResponsePojo.success(result);
    }

    /**
     * 批量新增楼栋、楼层和房间
     * 仅显示 buildingSize 和 builtArea 两个请求参数
     */
    @PostMapping("/addBuildings")
    @Operation(
            summary = "批量新增建筑",
            description = "给出第一个楼栋的全属性，然后给出还需要多少个楼栋，每层楼房间的建筑面积"
    )
    public ResponseEntity<?> addBuildings(
                @Parameter(description = "剩余需添加的楼栋个数", required = true)
                @RequestParam int buildingsize,

                @Parameter(description = "每层楼房间的建筑面积，按房间序号排列", required = true)
                @RequestParam List<Double> builtArea,@RequestBody(required = true) Building firstBuilding
    ) {
        if (firstBuilding == null) {
           ResponseEntity.badRequest().build();
        }
        // 你的处理逻辑
        return ResponseEntity.ok().build();
    }

    public ResponsePojo<Map<String, Integer>> addBuildingsAndFloorsAndRooms(
            @Parameter(hidden = true)
            @RequestBody(required = false) Building firstBuilding,  // Swagger隐藏，但仍传 null 或后台使用默认值

            @Parameter(description = "需要新增的楼栋个数", required = true, example = "3")
            @RequestParam Integer buildingSize,

            @Parameter(description = "每层楼房间的建筑面积，按房间序号排列", required = true, example = "[100,120,80]")
            @RequestParam List<Integer> builtArea
    ) {
        Map<String, Integer> results = new HashMap<>();
        results.put("building", 0);
        results.put("floor", 0);
        results.put("room", 0);

        try {
            List<Building> buildings = buildingService.insertBuildings(
                    firstBuilding,
                    buildingSize,
                    builtArea.toArray(new Integer[0])
            );
            results.put("building", buildings.size());

            for (Building b : buildings) {
                Floor floor = new Floor();
                floor.setBelongBuilding(b.getBuildingId());
                floor.setRoomNumber((short) builtArea.size());
                floor.setBuildingName(b.getBuildingName());

                floorService.insertFloors(floor, results, b.getBuildingFloors(), builtArea.toArray(new Integer[0]));
            }

            return ResponsePojo.success(results);

        } catch (DuplicateKeyException e) {
            return ResponsePojo.fail(null, e.getMessage());
        }
    }

    @Operation(summary = "批量修改多个建筑的状态")
    @Parameters({
            @Parameter(name = "idList", description = "需修改状态的楼栋 id 列表", required = true),
            @Parameter(name = "status", description = "修改后的状态", required = true)
    })


    @PutMapping("/updatestatus")
    public ResponsePojo<Integer> changeBuildingStatus(
            @RequestParam("idList") List<Integer> idList,
            @RequestParam("status") Integer status) {

        if (idList != null && !idList.isEmpty()) {
            try {
                return ResponsePojo.success(buildingService.changeBuildingStatus(idList, status));
            } catch (Exception e) {
                e.printStackTrace();
                return ResponsePojo.fail(null, "无数据");
            }
        } else {
            return ResponsePojo.fail(null, "参数错误");
        }
    }
}




