package com.epam.training.ticketservice.core.screening;

import com.epam.training.ticketservice.core.screening.model.ScreeningDto;

import java.util.Date;
import java.util.List;

public interface ScreeningService {

    void createScreening(ScreeningDto screeningDto);

    void deleteScreening(String movieTitle, String roomName, Date screeningStartDate);

    List<ScreeningDto> getScreeningList();
}
