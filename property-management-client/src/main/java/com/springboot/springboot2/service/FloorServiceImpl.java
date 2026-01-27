package com.springboot.springboot2.service;

import com.springboot.springboot2.mapper.FloorMapper;
import com.springboot.springboot2.pojo.Floor;
import com.springboot.springboot2.pojo.Room;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FloorServiceImpl implements FloorService {

    @Resource
    private FloorMapper floorMapper;

    @Autowired
    private RoomService roomService;

    @Override
    public List<Floor> floorsOfBuilding(Integer buildingId) {
        return floorMapper.floorsOfBuilding(buildingId);
    }

    /**
     * 批量新增楼层并生成房间，同时更新结果集
     *
     * @param floor      原始楼层对象
     * @param results    统计结果集（floor、room数量）
     * @param size       楼层数量
     * @param builtArea  每层楼房间面积数组
     */

    @Override
    public void insertFloors(Floor floor, Map<String, Integer> results, int size, Integer[] builtArea) {
        List<Floor> floorList = new ArrayList<>(size);

        // 1. 生成楼层列表
        for (int i = 0; i < size; i++) {
            Floor tempFloor = floor.clone(); // 使用 clone() 代替 copy()
            tempFloor.setFloorNumber((short) (i + 1));
            tempFloor.setRoomNumber((short) builtArea.length);
            floorList.add(tempFloor);
        }

        // 2. 批量插入楼层
        int insertedFloorCount = floorMapper.insertFloors(floorList);
        results.put("floor", results.getOrDefault("floor", 0) + insertedFloorCount);

        // 3. 遍历楼层，生成房间并插入
        for (Floor f : floorList) {
            List<Room> roomList = Arrays.stream(builtArea)
                    .map(area -> {
                        Room room = new Room();
                        room.setRoomFloorId(String.valueOf(f.getFloorId())); // 房间所属楼层ID
                        room.setRoomCode(
                                String.valueOf(f.getBuildingName()).replaceAll("\\D", "")  // 楼栋数字
                                        + "_"
                                        + f.getFloorNumber()
                                        + "_"
                        );
                        room.setBuiltUpArea(String.valueOf(area)); // 面积
                        return room;
                    })
                    .collect(Collectors.toList());

            int insertedRoomCount = roomService.insertRooms(roomList);
            results.put("room", results.getOrDefault("room", 0) + insertedRoomCount);
        }
    }
}
