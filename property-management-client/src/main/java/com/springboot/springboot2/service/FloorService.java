package com.springboot.springboot2.service;

import com.springboot.springboot2.pojo.Floor;

import java.util.List;
import java.util.Map;

public interface FloorService {
    List<Floor> floorsOfBuilding(Integer buildingId);
    //添加insertFloors方法
    void insertFloors(Floor floor, Map<String,Integer> results, int size,Integer[] builtArea);
}
