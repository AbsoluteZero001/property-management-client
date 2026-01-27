package com.springboot.springboot2.pojo;

import org.junit.jupiter.api.Test;

public class FloorTest {
    @Test
    public void testFloor() {
        Floor floor = new Floor();
        floor.setFloorId(2);
        floor.setBelongBuilding(2);
        floor.setFloorNumber(Short.parseShort("3"));
        floor.setRoomNumber(Short.parseShort("4"));
        System.out.println(floor);
    }
}

//Generated with love by TestMe :) Please raise issues & feature requests at: https://weirddev.com/forum#!/testme