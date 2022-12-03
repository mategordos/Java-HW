package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.movie.MovieService;
import com.epam.training.ticketservice.core.movie.model.MovieDto;
import com.epam.training.ticketservice.core.movie.persistence.entity.Movie;
import com.epam.training.ticketservice.core.user.UserService;
import com.epam.training.ticketservice.core.user.model.UserDto;
import com.epam.training.ticketservice.core.user.persistence.entity.User;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

@ShellComponent
@AllArgsConstructor
public class MovieCommand {

    private final MovieService movieService;
    private final UserService userService;


    @ShellMethod(key = "list movies", value = "List the existing movies")
    public String listMovies() {
        List<MovieDto> movieList = movieService.getMovieList();
        if (movieList.isEmpty()) {
            return ("There are no movies at the moment");
        } else {
            return movieList.stream()
                    .map(movie -> String.format("%s (%s, %d minutes)",
                            movie.getMovieTitle(), movie.getMovieGenre(), movie.getMovieLength()))
                    .collect(Collectors.joining("\n"));
        }
    }

    @ShellMethodAvailability("isAvailable")
    @ShellMethod(key = "create movie", value = "Create new movie")
    public void createMovie(String movieTitle, String movieGenre, Integer movieLength) {
        MovieDto movieDto = MovieDto.builder()
                .movieTitle(movieTitle)
                .movieGenre(movieGenre)
                .movieLength(movieLength)
                .build();
        movieService.createMovie(movieDto);
    }

    @ShellMethodAvailability("isAvailable")
    @ShellMethod(key = "update movie", value = "Update an existing movie")
    public void updateMovie(String movieTitle, String movieGenre, Integer movieLength) {
        MovieDto movieDto = MovieDto.builder()
                .movieTitle(movieTitle)
                .movieGenre(movieGenre)
                .movieLength(movieLength)
                .build();
        movieService.updateMovie(movieDto);
    }


    @ShellMethod(key = "delete movie", value = "Delete an existing movie")
    public void deleteMovie(String movieTitle) {
        movieService.deleteMovie(movieTitle);
    }


    private Availability isAvailable() {
        return userService.isAvailable();
    }


}
