package com.springboot.springboot2.service;

import com.springboot.springboot2.pojo.Building;
import com.springboot.springboot2.pojo.PageResult;
import org.springframework.dao.DuplicateKeyException;

import java.util.List;

public interface BuildingService {

    // 查询正在使用的楼栋
    List<Building> buildingsOfUsed();

    // 添加单个楼栋
    int insertBuilding(Building building) throws DuplicateKeyException;

    // 分页查询楼栋
    PageResult<Building> pageOfBuilding(int current, int size);

    // 批量新增楼栋，返回生成的楼栋列表（适配 Controller 调用）
    List<Building> insertBuildings(Building firstBuilding, int buildingSize, Integer[] builtArea);

    // 修改楼栋状态
    int changeBuildingStatus(List<Integer>idList,Integer status);
}
