package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.room.RoomService;
import com.epam.training.ticketservice.core.room.model.RoomDto;
import com.epam.training.ticketservice.core.user.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoomCommandTest {

    RoomService roomService = Mockito.mock(RoomService.class);
    UserService userService = Mockito.mock(UserService.class);
    RoomCommand underTest = new RoomCommand(roomService, userService);


    @Test
    void listRooms_noRooms() {
        Mockito.when(roomService.getRoomList()).thenReturn(List.of());

        assertEquals("There are no rooms at the moment", underTest.listRooms());
    }

    @Test
    void listRooms_withRooms() {
        Mockito.when(roomService.getRoomList()).thenReturn(List.of(
            RoomDto.builder()
                .roomName("RoomName")
                .rowLength(10)
                .columnLength(10)
                .build()
        ));

        assertEquals("Room RoomName with 100 seats, 10 rows and 10 columns", underTest.listRooms());
    }
}
