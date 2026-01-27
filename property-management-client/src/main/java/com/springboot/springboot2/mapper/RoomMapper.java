package com.springboot.springboot2.mapper;

import com.github.pagehelper.Page;
import com.springboot.springboot2.pojo.Room;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface RoomMapper {

    /**
     * 根据房间ID查询单个房间
     */
    Room roomById(@Param("id") Integer id);

    /**
     * 根据楼层ID查询房间列表
     */
    List<Room> roomsOfFloor(@Param("floorId") Integer floorId);

    /**
     * 根据用户的房间ID查询房间列表
     */
    List<Room> getRoomsByUserRoomId(@Param("userRoomId") Short userRoomId);

    /**
     * 批量插入房间
     * 注意：room_id 使用自增主键，因此在 XML 中可以传 null
     */
    int insertRooms(@Param("list") List<Room> roomList);

    /**
     * 分页查询房间信息，可带关键字筛选
     * 参数使用 Map 封装
     */
    Page<Room> pageOfRooms(@Param("params") Map<String, Object> params);

/**
 * 修改房间状态的方法
 * @param idList 房间ID列表，需要修改状态的房间ID集合
 * @param status 目标状态，要修改成的状态值
 * @return 返回值类型为int，可能表示受影响的行数或操作状态码
 */
    int changeRoomStatus(@Param("idList") List<Integer> idList, @Param("status") Integer status);
/**
 * 根据房间ID获取数量
 * @param roomId 房间ID参数
 * @return 返回指定房间ID的数量
 */
    int countById(@Param("roomId") Short roomId);
}
