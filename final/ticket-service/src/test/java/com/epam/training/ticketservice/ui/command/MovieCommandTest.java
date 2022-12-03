package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.movie.MovieService;
import com.epam.training.ticketservice.core.movie.model.MovieDto;
import com.epam.training.ticketservice.core.movie.persistence.entity.Movie;
import com.epam.training.ticketservice.core.user.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.shell.standard.ShellComponent;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@ShellComponent
public class MovieCommandTest {

    MovieService movieService = Mockito.mock(MovieService.class);
    UserService userService = Mockito.mock(UserService.class);
    MovieCommand underTest = new MovieCommand(movieService, userService);

    private static final Movie ENTITY = new Movie("Shrek", "animation", 90);
    private static final MovieDto DTO = MovieDto.builder()
            .movieTitle("Shrek")
            .movieGenre("animation")
            .movieLength(90)
            .build();

    private static final Movie ENTITY2 = new Movie("Shrek 2", "drama", 120);
    private static final MovieDto DTO2 = MovieDto.builder()
            .movieTitle("Shrek 2")
            .movieGenre("drama")
            .movieLength(120)
            .build();



    @Test
    public void testListMoviesCommandShouldReturnEmptyList() {
        // Given
        when(movieService.getMovieList()).thenReturn(Collections.emptyList());

        // When
        String output = underTest.listMovies();

        // Then
        assertEquals("There are no movies at the moment", output);
    }

    @Test
    public void testListMoviesCommandShouldReturnTwoMovies() {
        // Given
        List<MovieDto> movieList = Arrays.asList(DTO, DTO2);
        when(movieService.getMovieList()).thenReturn(movieList);

        // When
        String output = underTest.listMovies();

        // Then
        assertEquals("Shrek (animation, 90 minutes)\nShrek 2 (drama, 120 minutes)", output);
    }

    @Test
    public void testCreateMovie() {
        //Given
        String movieTitle = "Title 1";
        String movieGenre = "Genre 1";
        int movieLength = 150;

        // When
        underTest.createMovie(movieTitle, movieGenre, movieLength);

        // Then
        verify(movieService).createMovie(any());
    }

    @Test
    public void testUpdateMovie() {
        // Given
        String movieTitle = "Title 1";
        String movieGenre = "Genre 1";
        int movieLength = 120;

        // When
        underTest.updateMovie(movieTitle, movieGenre, movieLength);

        // Then
        verify(movieService).updateMovie(any());
    }


}
