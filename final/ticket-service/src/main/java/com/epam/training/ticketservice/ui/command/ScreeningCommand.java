package com.epam.training.ticketservice.ui.command;


import com.epam.training.ticketservice.core.room.model.RoomDto;
import com.epam.training.ticketservice.core.screening.ScreeningService;
import com.epam.training.ticketservice.core.screening.model.ScreeningDto;
import com.epam.training.ticketservice.core.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@ShellComponent
public class ScreeningCommand {

    private final ScreeningService screeningService;

    private final UserService userService;
    @ShellMethod(key = "create screening", value = "Create a new screening")
    public void createScreening(String movieTitle, String roomName, String screeningStartDate) throws ParseException {
        ScreeningDto screeningDto = ScreeningDto.builder()
                .movieTitle(movieTitle)
                .roomName(roomName)
                .screeningStartDate(convertStringToDate(screeningStartDate))
                .build();
        screeningService.createScreening(screeningDto);
    }

    @ShellMethod(key = "list screenings", value = "List the existing screenings")
    public String listScreenings() {
        List<ScreeningDto> screeningList = screeningService.getScreeningList();
        if (screeningList.isEmpty()) {
            return ("There are no screenings");
        } else {
            return screeningList.stream()
                    .map(screening -> String.format("%s (%s, %s)",
                            screening.getMovieTitle(),screening.getRoomName(),convertDateToString(screening.getScreeningStartDate())))
                    .collect(Collectors.joining("\n"));
        }
    }

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
}
