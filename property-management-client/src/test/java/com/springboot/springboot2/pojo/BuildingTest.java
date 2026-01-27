package com.springboot.springboot2.pojo;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class BuildingTest {
    @Test
    public void testBuilding() {
        Building building = new Building();
        building.setBuildingId(3);
        building.setBuildingName("Test Building");
        building.setBuildingCode("TSTB");
        building.setBuildingStatus((short) 1);
        building.setBuildingFloors((short) 10);
        building.setCompletionDate(LocalDate.now());
        building.setDesignLife((short) 10);
        building.setOwnership((short) 1);
        System.out.println(building.toString());
    }
    //Field completionDate of type LocalDate - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
}

//Generated with love by TestMe :) Please raise issues & feature requests at: https://weirddev.com/forum#!/testme