package com.epam.training.ticketservice.core.screening.model;


import com.epam.training.ticketservice.core.movie.persistence.entity.Movie;
import com.epam.training.ticketservice.core.room.persistence.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

import java.util.Date;

@Getter
@AllArgsConstructor
@Builder
public class ScreeningDto {
    private final String movieTitle;
    private final String roomName;
    private final Date screeningStartDate;
}
