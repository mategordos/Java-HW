package com.epam.training.ticketservice.core.movie;

import com.epam.training.ticketservice.core.movie.model.MovieDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface MovieService {
    List<MovieDto> getMovieList();
    Optional<MovieDto> getMovieByName(String movieTitle);

    void updateMovie(MovieDto movieDto);
    void createMovie(MovieDto movieDto);

    void deleteMovie(String movieTitle);
}
