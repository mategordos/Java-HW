package com.epam.training.ticketservice.core.room.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Getter
@AllArgsConstructor
@Builder
public class RoomDto {
    private final String roomName;
    private final int rowLength;
    private final int columnLength;
}
