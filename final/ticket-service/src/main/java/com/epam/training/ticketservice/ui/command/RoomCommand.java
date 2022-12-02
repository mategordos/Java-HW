package com.epam.training.ticketservice.ui.command;


import com.epam.training.ticketservice.core.movie.MovieService;
import com.epam.training.ticketservice.core.movie.model.MovieDto;
import com.epam.training.ticketservice.core.room.RoomService;
import com.epam.training.ticketservice.core.room.model.RoomDto;
import com.epam.training.ticketservice.core.room.persistence.entity.Room;
import com.epam.training.ticketservice.core.user.UserService;
import com.epam.training.ticketservice.core.user.model.UserDto;
import com.epam.training.ticketservice.core.user.persistence.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ShellComponent
@AllArgsConstructor
public class RoomCommand {


    private final RoomService roomService;

    private final UserService userService;


    @ShellMethod(key = "list rooms", value = "List the existing rooms")
    public String listRooms() {
        List<RoomDto> roomList = roomService.getRoomList();
        if (roomList.isEmpty()) {
            return ("There are no rooms at the moment");
        } else {
            return roomList.stream()
                    .map(room -> String.format("Room %s with %d seats, %d rows and %d columns",
                            room.getRoomName(), room.getRowLength() * room.getColumnLength(), room.getRowLength(), room.getColumnLength()))
                    .collect(Collectors.joining("\n"));
        }
    }

    @ShellMethodAvailability("isAvailable")
    @ShellMethod(key = "create room", value = "Create new room")
    public void createRoom(String roomName, Integer rowLength, Integer columnLength) {
       RoomDto roomDto = RoomDto.builder()
                .roomName(roomName)
                .rowLength(rowLength)
                .columnLength(columnLength)
                .build();
        roomService.createRoom(roomDto);
    }


    @ShellMethodAvailability("isAvailable")
    @ShellMethod(key = "update room", value = "Update an existing room")
    public void updateRoom(String roomName, Integer rowLength, Integer columnLength) {
        RoomDto roomDto = RoomDto.builder()
                .roomName(roomName)
                .rowLength(rowLength)
                .columnLength(columnLength)
                .build();
        roomService.updateRoom(roomDto);
    }


    @ShellMethodAvailability("isAvailable")
    @ShellMethod(key = "delete room", value = "Delete an existing room")
    public void deleteRoom(String roomName)
    {
        roomService.deleteRoom(roomName);
    }


    private Availability isAvailable() {
        Optional<UserDto> user = userService.describe();
        return user.isPresent() && user.get().getRole() == User.Role.ADMIN
                ? Availability.available()
                : Availability.unavailable("You are not an admin!");
    }



}
