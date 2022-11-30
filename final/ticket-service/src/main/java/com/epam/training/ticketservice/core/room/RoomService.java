package com.epam.training.ticketservice.core.room;

import com.epam.training.ticketservice.core.room.model.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    List<Room> getRoomList();

    Optional<Room> getRoomByName(String roomName);

    void createRoom(Room room);
}
