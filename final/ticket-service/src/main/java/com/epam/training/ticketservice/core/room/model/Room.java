package com.epam.training.ticketservice.core.room.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Room {
    private final String roomName;
    private final int rowLength;
    private final int columnLength;
}
