package com.epam.training.ticketservice.core.movie;

import com.epam.training.ticketservice.core.movie.model.MovieDto;
import com.epam.training.ticketservice.core.movie.persistence.entity.Movie;
import com.epam.training.ticketservice.core.movie.persistence.repository.MovieRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.hamcrest.CoreMatchers.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MovieServiceImplTest {

    private static final Movie ENTITY = new Movie("Shrek", "animation", 90);
    private static final MovieDto DTO = MovieDto.builder()
            .movieTitle("Shrek")
            .movieGenre("animation")
            .movieLength(90)
            .build();
    private final MovieRepository movieRepository = Mockito.mock(MovieRepository.class);
    private final MovieServiceImpl underTest = new MovieServiceImpl(movieRepository);


    @Test
    void testGetMovieListShouldReturnAStaticListOneElement() {
        // Given
        Movie movie = new Movie("Shrek", "animation", 90);
        when(movieRepository.findAll()).thenReturn(List.of(movie));
        List<MovieDto> expected = List.of(DTO);

        // Mockito.when
        List<MovieDto> actual = underTest.getMovieList();

        // Then
        assertEquals(expected.size(), actual.size());
        verify(movieRepository).findAll();
    }


    @Test
    void testCreateMovieShouldStoreTheGivenMovieWhenTheInputMovieIsValid() {
        // Given
        when(movieRepository.save(ENTITY)).thenReturn(ENTITY);

        // Mockito.when
        underTest.createMovie(DTO);

        // Then
        verify(movieRepository).save(any());
    }

    @Test
    void testDeleteMovieShouldDeleteMovieIfMovieExists() {
        //Given
        when(movieRepository.findMovieByMovieTitle(ENTITY.getMovieTitle())).thenReturn(Optional.of(ENTITY));

        //When
        underTest.deleteMovie(ENTITY.getMovieTitle());

        //Then
        verify(movieRepository).deleteMovieByMovieTitle(ENTITY.getMovieTitle());
    }

    @Test
    void testUpdateMovie_exists() {
        when(movieRepository.findMovieByMovieTitle(ENTITY.getMovieTitle())).thenReturn(Optional.of(ENTITY));

        underTest.updateMovie(MovieDto.builder()
            .movieGenre("romance")
            .movieLength(123)
            .movieTitle("Shrek")
            .build());

        verify(movieRepository).save(any());
    }

    @Test()
    void testUpdateMovie_nonExistingMovie() {
        when(movieRepository.findMovieByMovieTitle(ENTITY.getMovieTitle())).thenReturn(Optional.empty());

        MovieDto updatedDto = MovieDto.builder()
            .movieGenre("romance")
            .movieLength(123)
            .movieTitle("Shrek")
            .build();

        assertThrows(IllegalArgumentException.class, () ->  underTest.updateMovie(updatedDto));
    }
}
