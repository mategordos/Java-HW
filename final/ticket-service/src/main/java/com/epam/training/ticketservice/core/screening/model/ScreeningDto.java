package com.epam.training.ticketservice.core.screening.model;


import com.epam.training.ticketservice.core.movie.persistence.entity.Movie;
import com.epam.training.ticketservice.core.room.persistence.entity.Room;
import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Value
@Builder
public class ScreeningDto {
    private final String movieTitle;
    private final String roomName;
    private final Date screeningStartDate;
}
