package com.epam.training.ticketservice.ui.command;


import com.epam.training.ticketservice.core.movie.MovieService;
import com.epam.training.ticketservice.core.movie.persistence.entity.Movie;
import com.epam.training.ticketservice.core.movie.persistence.repository.MovieRepository;
import com.epam.training.ticketservice.core.room.model.RoomDto;
import com.epam.training.ticketservice.core.screening.ScreeningService;
import com.epam.training.ticketservice.core.screening.model.ScreeningDto;
import com.epam.training.ticketservice.core.user.UserService;
import com.epam.training.ticketservice.core.user.model.UserDto;
import com.epam.training.ticketservice.core.user.persistence.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@ShellComponent
public class ScreeningCommand {


    private final MovieRepository movieRepository;
    private final ScreeningService screeningService;

    private final UserService userService;
    //Meg kell néznem az összes meglévő screeninget, majd
    @ShellMethodAvailability("isAvailable")
    @ShellMethod(key = "create screening", value = "Create a new screening")
    public void createScreening(String movieTitle, String roomName, String screeningStartDate) throws ParseException {
        if() {
            ScreeningDto screeningDto = ScreeningDto.builder()
                    .movieTitle(movieTitle)
                    .roomName(roomName)
                    .screeningStartDate(convertStringToDate(screeningStartDate))
                    .build();
            screeningService.createScreening(screeningDto);
        }
    }
    @ShellMethodAvailability("isAvailable")
    @ShellMethod(key = "list screenings", value = "List the existing screenings")
    public String listScreenings() {
        List<ScreeningDto> screeningList = screeningService.getScreeningList();
        if (screeningList.isEmpty()) {
            return ("There are no screenings");
        } else {
            return screeningList.stream()
                    .map(screening -> {
                        Movie movie = movieRepository.findMovieByMovieTitle(screening.getMovieTitle()).get();
                        return String.format("%s, screened in room %s, at %s",
                                movie, screening.getRoomName(), convertDateToString(screening.getScreeningStartDate()));
                    })
                    .collect(Collectors.joining("\n"));
        }
    }

    @ShellMethodAvailability("isAvailable")
    @ShellMethod(key = "delete screening", value = "Delete an existing screening")
    public void deleteScreening(String movieTitle, String roomName, String screeningStartDate) throws ParseException {
        screeningService.deleteScreening(movieTitle, roomName, convertStringToDate(screeningStartDate));
    }


    private Date convertStringToDate(String date) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm")
                .parse(date);
    }

    private String convertDateToString(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm")
                .format(date);
    }


    private Availability isAvailable() {
        Optional<UserDto> user = userService.describe();
        return user.isPresent() && user.get().getRole() == User.Role.ADMIN
                ? Availability.available()
                : Availability.unavailable("You are not an admin!");
    }
}
