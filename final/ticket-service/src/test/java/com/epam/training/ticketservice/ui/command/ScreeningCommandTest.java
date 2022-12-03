package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.movie.persistence.entity.Movie;
import com.epam.training.ticketservice.core.movie.persistence.repository.MovieRepository;
import com.epam.training.ticketservice.core.screening.ScreeningService;
import com.epam.training.ticketservice.core.screening.model.ScreeningDto;
import com.epam.training.ticketservice.core.user.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;
import java.util.List;
import java.util.Optional;

class ScreeningCommandTest {

    ScreeningService screeningService = Mockito.mock(ScreeningService.class);
    MovieRepository movieRepository = Mockito.mock(MovieRepository.class);
    UserService userService = Mockito.mock(UserService.class);
    ScreeningCommand underTest = new ScreeningCommand(movieRepository, screeningService, userService);

    @Test
    void listScreenings_noScreenings() {
        Mockito.when(screeningService.getScreeningList()).thenReturn(List.of());

        Assertions.assertEquals("There are no screenings", underTest.listScreenings());
    }

    @Test
    void listScreenings_withScreenings() {
        Mockito.when(screeningService.getScreeningList()).thenReturn(List.of(
            ScreeningDto.builder()
                .movieTitle("Moovie")
                .screeningStartDate(new Date(1568119740000L))
                .build()
        ));

        Mockito.when(movieRepository.findMovieByMovieTitle("Moovie"))
            .thenReturn(Optional.of(new Movie("Moovie", "nature", 123)));

        Assertions.assertEquals("Moovie (nature, 123 minutes), screened in room null, at 2019-09-10 14:49", underTest.listScreenings());
    }
}
