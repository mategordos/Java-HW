package com.epam.training.ticketservice.core.movie.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Getter
@AllArgsConstructor
@Builder
public class MovieDto {
    private final String movieTitle;
    private final String movieGenre;
    private final int movieLength;
}
