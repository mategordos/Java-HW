package com.epam.training.ticketservice.core.screening;

import com.epam.training.ticketservice.core.movie.MovieService;
import com.epam.training.ticketservice.core.movie.persistence.entity.Movie;
import com.epam.training.ticketservice.core.movie.persistence.repository.MovieRepository;
import com.epam.training.ticketservice.core.room.persistence.entity.Room;
import com.epam.training.ticketservice.core.room.persistence.repository.RoomRepository;
import com.epam.training.ticketservice.core.screening.model.ScreeningDto;
import com.epam.training.ticketservice.core.screening.persistence.entity.Screening;
import com.epam.training.ticketservice.core.screening.persistence.repository.ScreeningRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ScreeningServiceImpl implements ScreeningService {

    private final MovieRepository movieRepository;
    private final RoomRepository roomRepository;
    private final ScreeningRepository screeningRepository;


    @Override
    public void deleteScreening(String movieTitle, String roomName, Date screeningStartDate) {
        screeningRepository.deleteByMovie_MovieTitleLikeAndRoom_RoomNameLikeAndScreeningStartDate(
                movieTitle, roomName, screeningStartDate);
    }

    @Override
    public void createScreening(ScreeningDto screeningDto) {
        List<Screening> sameRoomScreenings = screeningRepository.findByRoom_RoomName(
                screeningDto.getRoomName());
        Date newScreeningStart = screeningDto.getScreeningStartDate();
        int newScreeningLength = movieRepository.findMovieByMovieTitle(
                screeningDto.getMovieTitle()).get().getMovieLength();

        // Keep track of whether the new screening overlaps with an existing one or not
        boolean overlaps = false;

        for (var storedScreening : sameRoomScreenings) {
            Date storedScreeningStart = storedScreening.getScreeningStartDate();
            int storedScreeningLength = storedScreening.getMovie().getMovieLength();

            if (newScreeningStart.after(DateUtils.addMinutes(
                            storedScreeningStart, -1 * (newScreeningLength + 10)))
                    && (newScreeningStart.before(DateUtils.addMinutes(
                            storedScreeningStart, storedScreeningLength))
                    || newScreeningStart.equals(DateUtils.addMinutes(
                            storedScreeningStart, storedScreeningLength)))) {
                System.out.println("There is an overlapping screening");
                overlaps = true;
                break;
            } else if (newScreeningStart.after(DateUtils.addMinutes(
                            storedScreeningStart, storedScreeningLength))
                    && newScreeningStart.before(DateUtils.addMinutes(
                            storedScreeningStart, storedScreeningLength + 10))
                    || newScreeningStart.equals(DateUtils.addMinutes(
                            storedScreeningStart, storedScreeningLength + 10))) {
                System.out.println("This would start in the break period after another screening in this room");
                overlaps = true;
                break;
            }
        }

        // If the new screening does not overlap with any existing ones, create and save it
        if (!overlaps) {
            Movie movie = movieRepository.findMovieByMovieTitle(screeningDto.getMovieTitle()).get();
            Room room = roomRepository.findRoomByRoomName(screeningDto.getRoomName()).get();

            Screening screening = new Screening(movie,
                    room,
                    screeningDto.getScreeningStartDate());
            screeningRepository.save(screening);
        }
    }




    @Override
    public List<ScreeningDto> getScreeningList() {
        return screeningRepository.findAll().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    private ScreeningDto convertEntityToDto(Screening screening) {
        return ScreeningDto.builder()
                .movieTitle(screening.getMovie().getMovieTitle())
                .roomName(screening.getRoom().getRoomName())
                .screeningStartDate(screening.getScreeningStartDate())
                .build();
    }









}
