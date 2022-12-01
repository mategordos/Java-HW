package com.epam.training.ticketservice.core.room;


import com.epam.training.ticketservice.core.movie.model.MovieDto;
import com.epam.training.ticketservice.core.movie.persistence.entity.Movie;
import com.epam.training.ticketservice.core.room.model.RoomDto;
import com.epam.training.ticketservice.core.room.persistence.entity.Room;
import com.epam.training.ticketservice.core.room.persistence.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public List<RoomDto> getRoomList() {

        return roomRepository.findAll().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }
    @Override
    public void createRoom(RoomDto roomDto) {
        Room room = new Room(roomDto.getRoomName(),
                roomDto.getRowLength(),
                roomDto.getColumnLength());
        roomRepository.save(room);
    }

    @Override
    public void deleteRoom(String roomName) {
        roomRepository.deleteByRoomName(roomName);
    }


    public void updateRoom(RoomDto roomDto) {
        Optional<Room> room = roomRepository.findRoomByRoomName(roomDto.getRoomName());
        if (room.isPresent())
        {
            room.get().setRowLength(roomDto.getRowLength());
            room.get().setColumnLength(roomDto.getColumnLength());
            roomRepository.save(room.get());
        } else {
            throw new IllegalArgumentException("Room does not exist!");
        }
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
