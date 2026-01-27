package com.springboot.springboot2.pojo;

import org.junit.jupiter.api.Test;

public class RoomTest {
    @Test
    public void testRoom() {
        Room room = new Room();
        room.setRoomId("1");
        room.setRoomFloorId("1");
        room.setBuiltUpArea("100");
        room.setRoomStatus("1");
        room.setRoomCode("1-1-1");
        System.out.println(room);
    }
}
