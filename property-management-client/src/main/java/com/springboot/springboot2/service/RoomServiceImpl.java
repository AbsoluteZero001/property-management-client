package com.springboot.springboot2.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.springboot.springboot2.mapper.RoomMapper;
import com.springboot.springboot2.pojo.PageResult;
import com.springboot.springboot2.pojo.Room;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RoomServiceImpl implements RoomService {

    @Resource
    private RoomMapper roomMapper;

    @Override
    public List<Room> roomsOfFloor(Integer floorId) {
        return roomMapper.roomsOfFloor(floorId);
    }

    @Override
    public int insertRooms(List<Room> rooms) {
        return roomMapper.insertRooms(rooms); // RoomMapper 需要实现批量插入
    }

    @Override
    public PageResult<Room> pageOfRoom(Map<String, String> params) {
        int current = Integer.parseInt(params.get("current"));
        int size = Integer.parseInt(params.get("size"));

        PageHelper.startPage(current, size);

        // 转换 Map<String, String> -> Map<String, Object>
        Map<String, Object> queryParams = new java.util.HashMap<>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            queryParams.put(entry.getKey(), entry.getValue());
        }

        Page<Room> rooms = roomMapper.pageOfRooms(queryParams);

        return PageResult.restPage(rooms);
    }

    @Override
    public int changeRoomStatus(List<Integer> idList, int status) {
        return roomMapper.changeRoomStatus(idList, status);
    }
//    @Override
//    public boolean existsById(Short roomId) {
//        // 调用 RoomMapper 查询房间数量，如果大于0表示存在
//        return roomMapper.countById(roomId) > 0;
//    }

}
