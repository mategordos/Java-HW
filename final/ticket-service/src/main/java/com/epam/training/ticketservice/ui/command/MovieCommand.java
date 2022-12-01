package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.movie.MovieService;
import com.epam.training.ticketservice.core.movie.model.MovieDto;
import com.epam.training.ticketservice.core.user.UserService;
import com.epam.training.ticketservice.core.user.model.UserDto;
import com.epam.training.ticketservice.core.user.persistence.entity.User;
import java.util.List;
import java.util.Optional;
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
    public List<MovieDto> listMovies() {
        List<MovieDto> movieList = movieService.getMovieList();
        if (movieList.isEmpty()) {
            System.out.println("There are no movies at the moment");
        }
        return movieList;
    }
    @ShellMethodAvailability("isAvailable")
    @ShellMethod(key = "create movie", value = "Create new movie")
    public MovieDto createMovie(String movieTitle, String movieGenre, Integer movieLength) {
        MovieDto movieDto = MovieDto.builder()
                .movieTitle(movieTitle)
                .movieGenre(movieGenre)
                .movieLength(movieLength)
                .build();
        movieService.createMovie(movieDto);
        return movieDto;
    }

    @ShellMethodAvailability("isAvailable")
    @ShellMethod(key = "update movie", value = "Update an existing movie")
    public MovieDto updateMovie(String movieTitle, String movieGenre, Integer movieLength) {
        MovieDto movieDto = MovieDto.builder()
                .movieTitle(movieTitle)
                .movieGenre(movieGenre)
                .movieLength(movieLength)
                .build();
        movieService.updateMovie(movieDto);
        return movieDto;
    }


    //not in use yet
    private Availability isAvailable() {
        Optional<UserDto> user = userService.describe();
        return user.isPresent() && user.get().getRole() == User.Role.ADMIN
                ? Availability.available()
                : Availability.unavailable("You are not an admin!");
    }
}
