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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ScreeningServiceImpl implements ScreeningService{

    private final MovieRepository movieRepository;
    private final RoomRepository roomRepository;
    private final ScreeningRepository screeningRepository;


    //Refactor later so it's not a mess
    @Override
    public void createScreening(ScreeningDto screeningDto) {
        if(movieRepository.findMovieByMovieTitle(screeningDto.getMovieTitle()).isPresent() &&
        roomRepository.findRoomByRoomName(screeningDto.getRoomName()).isPresent()) {
            Movie movie = movieRepository.findMovieByMovieTitle(screeningDto.getMovieTitle()).get();
            Room room = roomRepository.findRoomByRoomName(screeningDto.getRoomName()).get();
            Screening screening = new Screening(movie, room, screeningDto.getScreeningStartDate());
            screeningRepository.save(screening);
        }
    }

    @Override
    public void deleteScreening(ScreeningDto screeningDto) {
        screeningRepository.deleteScreeningByScreeningDto(screeningDto);
    }

    @Override
    public List<ScreeningDto> getScreeningList() {
        return screeningRepository.findAll().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    private ScreeningDto convertEntityToDto(Screening screening){
        return ScreeningDto.builder()
                .movieTitle(screening.getMovie().getMovieTitle())
                .roomName(screening.getRoom().getRoomName())
                .screeningStartDate(screening.getScreeningStartDate()).
                build();
    }


    private Optional<ScreeningDto> convertEntityToDto(Optional<Screening> screening) {
        return screening.isEmpty() ? Optional.empty() : Optional.of(convertEntityToDto(screening.get()));
    }

}
