package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.movie.MovieService;
import com.epam.training.ticketservice.core.movie.model.MovieDto;
import com.epam.training.ticketservice.core.user.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.shell.standard.ShellComponent;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ShellComponent
public class MovieCommandTest {


    // Create mock objects for the dependencies
    MovieService movieService = Mockito.mock(MovieService.class);
    UserService userService = Mockito.mock(UserService.class);

    @Test
    public void testListMovies_emptyList() {

        // Create a new MovieCommand instance and inject the mock dependencies
        MovieCommand movieCommand = new MovieCommand(movieService, userService);

        // Given
        when(movieService.getMovieList()).thenReturn(Collections.emptyList());

        // When
        String output = movieCommand.listMovies();

        // Then
        assertEquals("There are no movies at the moment", output);
    }

    @Test
    public void testListMovies() {
        // Create a new MovieCommand instance and inject the mock dependencies
        MovieCommand movieCommand = new MovieCommand(movieService, userService);

        // Given
        List<MovieDto> movieList = Arrays.asList(
                MovieDto.builder()
                        .movieTitle("Title 1")
                        .movieGenre("Genre 1")
                        .movieLength(120)
                        .build(),
                MovieDto.builder()
                        .movieTitle("Title 2")
                        .movieGenre("Genre 2")
                        .movieLength(100)
                        .build()
        );
        when(movieService.getMovieList()).thenReturn(movieList);

        // When
        String output = movieCommand.listMovies();

        // Then
        assertEquals("Title 1 (Genre 1, 120 minutes)\nTitle 2 (Genre 2, 100 minutes)", output);
    }



}