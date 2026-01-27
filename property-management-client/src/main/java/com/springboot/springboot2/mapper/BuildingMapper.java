package com.springboot.springboot2.mapper;

import com.springboot.springboot2.pojo.Building;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BuildingMapper {

    /**
     * 根据状态查询楼栋
     * @param status 楼栋状态
     * @return 楼栋列表
     */
    List<Building> buildingsOfStatus(int status);

    /**
     * 插入单个楼栋
     * @param building 楼栋对象
     * @return 影响的行数
     */
    int insertBuilding(Building building);

    /**
     * 批量插入多个楼栋
     * @param buildings 楼栋列表
     * @return 插入成功的行数
     */
    int insertBuildings(List<Building> buildings);

    /**
     * 批量修改多个建筑的状态
     * @param idList 建筑 ID 列表
     * @param status 修改后的状态
     * @return 受影响的行数
     */
    int changeBuildingStatus(@Param("idList") List<Integer> idList,
                             @Param("status") Integer status);
}
