package com.springboot.springboot2.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.springboot2.mapper.BuildingMapper;
import com.springboot.springboot2.pojo.Building;
import com.springboot.springboot2.pojo.PageResult;
import com.springboot.springboot2.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingMapper buildingMapper;

    @Override
    public List<Building> buildingsOfUsed() {
        return buildingMapper.buildingsOfStatus(1);
    }

    @Override
    public int insertBuilding(Building building) throws DuplicateKeyException {
        if (building.getBuildingName() == null || building.getBuildingName().isEmpty()) {
            throw new IllegalArgumentException("楼栋名称不能为空");
        }
        if (building.getBuildingCode() == null || building.getBuildingCode().isEmpty()) {
            throw new IllegalArgumentException("楼栋编码不能为空");
        }
        if (building.getCompletionDate() == null) {
            building.setCompletionDate(LocalDate.now());
        }
        if (building.getBuildingStatus() == null) {
            building.setBuildingStatus((short) 1);
        }
        if (building.getBuildingFloors() == null) {
            building.setBuildingFloors((short) 1);
        }
        if (building.getDesignLife() == null) {
            building.setDesignLife((short) 50);
        }
        if (building.getOwnership() == null) {
            building.setOwnership((short) 1);
        }
        return buildingMapper.insertBuilding(building);
    }

    @Override
    public PageResult<Building> pageOfBuilding(int current, int size) {
        PageHelper.startPage(current, size);
        List<Building> list = buildingMapper.buildingsOfStatus(0);
        PageInfo<Building> pageInfo = new PageInfo<>(list);
        return new PageResult<>(pageInfo.getList(), pageInfo.getTotal(), current, size);
    }

    /**
     * 批量新增楼栋，返回生成的楼栋列表（兼容Controller）
     *
     * @param building     第一个楼栋
     * @param buildingSize 需要新增的总楼栋数（包含第一个）
     * @param builtArea    每层楼房间面积数组（用于后续楼层生成）
     * @return 新增的楼栋列表
     */
    @Override
    public List<Building> insertBuildings(Building building, int buildingSize, Integer[] builtArea) {
        List<Building> buildings = new ArrayList<>();
        buildings.add(building);

        for (int i = 1; i < buildingSize; i++) { // 第一个楼栋已经在列表里
            Building tempBuilding = building.cloneBuilding();

            // 生成楼栋编码
            String code = building.getBuildingCode();
            int codeNum = StringUtil.getNumberFromString(code) + i;
            code = code.replaceAll("\\d+", StringUtil.numberToLen2String(codeNum));
            tempBuilding.setBuildingCode(code);

            // 生成楼栋名称
            String name = building.getBuildingName();
            int nameNum = StringUtil.getNumberFromString(name) + i;
            name = name.replaceAll("\\d+", StringUtil.numberToLen2String(nameNum));
            tempBuilding.setBuildingName(name);

            buildings.add(tempBuilding);
        }

        // 批量插入到数据库
        buildingMapper.insertBuildings(buildings);

        return buildings;
    }

    @Override
    public int changeBuildingStatus(List<Integer> idList, Integer status) {
        return buildingMapper.changeBuildingStatus(idList, status);
    }
}
