package com.epam.training.ticketservice.core.room;

import com.epam.training.ticketservice.core.room.model.RoomDto;
import com.epam.training.ticketservice.core.room.persistence.entity.Room;
import com.epam.training.ticketservice.core.room.persistence.repository.RoomRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.ArgumentMatchers.any;

class RoomServiceImplTest {

    private static final Room ENTITY = new Room("The Dark Room", 10, 10);
    private static final RoomDto DTO = RoomDto.builder()
            .roomName("The Dark Room")
            .rowLength(10)
            .columnLength(10)
            .build();
    private final RoomRepository roomRepository = Mockito.mock(RoomRepository.class);
    private final RoomServiceImpl underTest = new RoomServiceImpl(roomRepository);


    @Test
    void testGetRoomListShouldReturnAStaticListWithOneElement() {
        // Given
        Mockito.when(roomRepository.findAll()).thenReturn(List.of(ENTITY));
        List<RoomDto> expected = List.of(DTO);

        // Mockito.when
        List<RoomDto> actual = underTest.getRoomList();

        // Then
        assertEquals(expected.size(), actual.size());
    }

    @Test
    void testCreateRoomShouldStoreTheGivenRoomWhenTheInputRoomIsValid() {
        // Given
        Mockito.when(roomRepository.save(ENTITY)).thenReturn(ENTITY);

        // Mockito.when
        underTest.createRoom(DTO);

        // Then
        Mockito.verify(roomRepository).save(any());
    }

    @Test
    void updateRoom() {
    }

    @Test
    void testDeleteRoomShouldDeleteRoomIfRoomExists() {
        //Given
        Mockito.when(roomRepository.findRoomByRoomName(ENTITY.getRoomName())).thenReturn(Optional.of(ENTITY));

        //When
        underTest.deleteRoom(ENTITY.getRoomName());

        //Then
        Mockito.verify(roomRepository).deleteByRoomName(ENTITY.getRoomName());
    }

    @Test
    void testUpdateRoom_exists() {
        Mockito.when(roomRepository.findRoomByRoomName(any())).thenReturn(Optional.of(ENTITY));

        RoomDto updatedDto = RoomDto
            .builder()
            .roomName("The White Room")
            .columnLength(10)
            .rowLength(10)
            .build();

        underTest.updateRoom(updatedDto);

        Mockito.verify(roomRepository).save(any());
    }

    @Test
    void testUpdateRoom_notExists() {
        Mockito.when(roomRepository.findRoomByRoomName(any())).thenReturn(Optional.empty());

        RoomDto updatedDto = RoomDto
            .builder()
            .roomName("The White Room")
            .columnLength(10)
            .rowLength(10)
            .build();



        Assertions.assertThrows(IllegalArgumentException.class, () -> underTest.updateRoom(updatedDto));
    }
}
