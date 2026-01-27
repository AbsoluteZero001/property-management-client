package com.springboot.springboot2.mapper;

import com.springboot.springboot2.pojo.Floor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FloorMapper {

    /**
     * 批量插入楼层
     */
    int insertFloors(List<Floor> floors);

    /**
     * 根据楼栋ID查询楼层列表
     */
    List<Floor> floorsOfBuilding(Integer id);
}
