package com.epam.training.ticketservice.core.room;


import com.epam.training.ticketservice.core.room.model.RoomDto;
import com.epam.training.ticketservice.core.room.persistence.entity.Room;
import com.epam.training.ticketservice.core.room.persistence.repository.RoomRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public List<RoomDto> getRoomList() {

        return roomRepository.findAll().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }
    @Override
    public Optional<RoomDto> getRoomByName(String roomName) {
        return roomDtoList.stream()
                .filter(roomDto -> roomDto.getRoomName().equals(roomName))
                .findFirst();
    }

    @Override
    public void createRoom(RoomDto roomDto) {
        roomDtoList.add(roomDto);
    }




    private RoomDto convertEntityToDto(Room room){
        return  RoomDto.builder()
                .roomName(room.getRoomName())
                .rowLength(room.getRowLength())
                .columnLength(room.getColumnLength()).
                build();
    }


    private Optional<RoomDto> convertEntityToDto(Optional<Room> room) {
        return room.isEmpty() ? Optional.empty() : Optional.of(convertEntityToDto(room.get()));
    }

}
