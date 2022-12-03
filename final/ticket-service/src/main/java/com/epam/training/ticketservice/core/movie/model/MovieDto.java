package com.epam.training.ticketservice.core.movie.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MovieDto {
    private final String movieTitle;
    private final String movieGenre;
    private final int movieLength;
}
