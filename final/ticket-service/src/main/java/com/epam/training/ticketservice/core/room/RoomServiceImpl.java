package com.epam.training.ticketservice.core.room;


import com.epam.training.ticketservice.core.room.model.Room;

import java.util.List;
import java.util.Optional;

public class RoomServiceImpl implements RoomService {

    private List<Room> roomList;

    @Override
    public List<Room> getRoomList() {
        return null;
    }

    @Override
    public Optional<Room> getRoomByName(String roomName) {
        return roomList.stream()
                .filter(room -> room.getRoomName().equals(roomName))
                .findFirst();
    }

    @Override
    public void createRoom(Room room) {
        roomList.add(room);
    }
}
