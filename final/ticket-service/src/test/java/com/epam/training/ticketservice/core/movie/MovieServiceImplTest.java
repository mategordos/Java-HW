package com.epam.training.ticketservice.core.movie;

import com.epam.training.ticketservice.core.movie.model.MovieDto;
import com.epam.training.ticketservice.core.movie.persistence.entity.Movie;
import com.epam.training.ticketservice.core.movie.persistence.repository.MovieRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

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
        when(movieRepository.findAll()).thenReturn(List.of(ENTITY));
        List<MovieDto> expected = List.of(DTO);

        // Mockito.when
        List<MovieDto> actual = underTest.getMovieList();

        // Then
        Assertions.assertEquals(expected, actual);
        verify(movieRepository).findAll();
    }


    @Test
    void testCreateMovieShouldStoreTheGivenMovieWhenTheInputMovieIsValid() {
        // Given
        when(movieRepository.save(ENTITY)).thenReturn(ENTITY);

        // Mockito.when
        underTest.createMovie(DTO);

        // Then
        verify(movieRepository).save(ENTITY);
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
}