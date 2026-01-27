package com.springboot.springboot2.controller;

import com.springboot.springboot2.pojo.PageResult;
import com.springboot.springboot2.pojo.ResponsePojo;
import com.springboot.springboot2.pojo.Room;
import com.springboot.springboot2.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "房间相关API")
@RequestMapping("/room")
@RestController
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/roomsOfFloor/{floorId}")
    @Operation(summary = "根据楼层ID查询房间列表")
    public ResponsePojo<List<Room>> roomsOfFloor(
            @Parameter(description = "楼层ID", required = true)
            @PathVariable("floorId") Integer floorId) {

        List<Room> list = roomService.roomsOfFloor(floorId);

        if (list != null && !list.isEmpty()) {
            return ResponsePojo.success(list);
        } else {
            return ResponsePojo.fail(list, "floorId 查询不到对应房间");
        }
    }

    @PostMapping("/pageOfRooms")
    @Operation(
            summary = "分页查询所有房间信息，可以带关键字查询",
            description = "application/json 格式<br/>" +
                    "{current: 当前页, default 1, required false,<br/>" +
                    "size: 每页数据量, default 10, required false,<br/>" +
                    "builtArea: 建筑面积, required false,<br/>" +
                    "status: 房间状态, required false,<br/>" +
                    "floorNumber: 房间所属楼层数, required false,<br/>" +
                    "buildingNumber: 房间所属建筑号, required false}"
    )

    public ResponsePojo<PageResult<Room>> pageOfRooms(
            @RequestBody Map<String, String> params) {

        // 设置默认分页参数
        params.putIfAbsent("current", "1");
        params.putIfAbsent("size", "10");

        // 调用服务层获取分页结果
        PageResult<Room> result = roomService.pageOfRoom(params);

        return ResponsePojo.success(result);
    }

    /**
     * 批量修改房间状态
     */
    @PutMapping("/updatestatus")
    @Operation(summary = "批量修改房间状态", description = "页面需要勾选多个房间")
    public ResponsePojo<Integer> changeRoomStatus(
            @Parameter(description = "需修改状态的房间ID列表", required = true)
            @RequestParam List<Integer> idList,
            @Parameter(description = "修改后的状态", required = true)
            @RequestParam Integer status) {

        if (idList == null || idList.isEmpty()) {
            return ResponsePojo.fail(null, "无数据");
        }

        try {
            int result = roomService.changeRoomStatus(idList, status);
            return ResponsePojo.success(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponsePojo.fail(null, "修改失败：" + e.getMessage());
        }
    }
}
