    package com.epam.training.ticketservice.core.screening;

    import com.epam.training.ticketservice.core.movie.persistence.entity.Movie;
    import com.epam.training.ticketservice.core.movie.persistence.repository.MovieRepository;
    import com.epam.training.ticketservice.core.room.persistence.entity.Room;
    import com.epam.training.ticketservice.core.room.persistence.repository.RoomRepository;
    import com.epam.training.ticketservice.core.screening.model.ScreeningDto;
    import com.epam.training.ticketservice.core.screening.persistence.entity.Screening;
    import com.epam.training.ticketservice.core.screening.persistence.repository.ScreeningRepository;
    import org.junit.jupiter.api.Test;
    import org.mockito.Mockito;

    import java.text.SimpleDateFormat;
    import java.util.Date;
    import java.util.List;
    import java.util.Optional;

    import static org.mockito.Mockito.mock;

    public class ScreeningServiceImplTest {
        private static final Movie MOVIE = new Movie("Shrek", "animation", 169);
        private static final Room ROOM = new Room("RoomOne", 5, 10);

        private static final Movie MOVIE2 = new Movie("Shrek 2", "myth", 90);
        private static final Room ROOM2 = new Room("RoomTwo", 4, 10);

        private static final Movie MOVIE3 = new Movie("Shrek 3", "crime", 150);
        private static final Room ROOM3 = new Room("RoomThree", 3, 10);
        private static final Date DATE = convertStringToDate("2021-03-15 10:45");
        private static final Date DATE2 = convertStringToDate("2021-03-15 13:37");
        private static final Date DATE3 = convertStringToDate("2021-03-15 10:40");
        private static final Screening ENTITY = new Screening(MOVIE, ROOM, DATE);
        private static final ScreeningDto DTO = ScreeningDto.builder()
                .movieTitle(MOVIE.getMovieTitle())
                .roomName(ROOM.getRoomName())
                .screeningStartDate(DATE)
                .build();
        private static final ScreeningDto DTO2 = ScreeningDto.builder()
                .movieTitle(MOVIE.getMovieTitle())
                .roomName(ROOM.getRoomName())
                .screeningStartDate(DATE)
                .build();
        private static final ScreeningDto DTO3 = ScreeningDto.builder()
                .movieTitle(MOVIE.getMovieTitle())
                .roomName(ROOM.getRoomName())
                .screeningStartDate(DATE)
                .build();

        private final MovieRepository movieRepository = mock(MovieRepository.class);
        private final RoomRepository roomRepository = mock(RoomRepository.class);
        private final ScreeningRepository screeningRepository = mock(ScreeningRepository.class);
        private final ScreeningServiceImpl underTest = new ScreeningServiceImpl(movieRepository,roomRepository,screeningRepository);

/*
        @Test
        void testCreateScreeningShouldStoreScreeningIfInputScreeningIsValid() {
            // Given
            Mockito.when(screeningRepository.findByRoom_RoomName(ROOM.getRoomName())).thenReturn(List.of(ENTITY));
            Mockito.when(roomRepository.findRoomByRoomName(ROOM.getRoomName())).thenReturn(Optional.of(ROOM));
            Mockito.when(movieRepository.findMovieByMovieTitle(MOVIE.getMovieTitle())).thenReturn(Optional.of(MOVIE));
            Mockito.when(screeningRepository.findByScreeningStartDateAfterAndRoom_RoomName(DATE, ROOM.getRoomName())).thenReturn(List.of(ENTITY));
            Mockito.when(screeningRepository.save(ENTITY)).thenReturn(ENTITY);

            // When
            underTest.createScreening(DTO);

            // Then
            Mockito.verify(screeningRepository).save(ENTITY);
        }
        */

        static Date convertStringToDate(String date) {
            try {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm")
                        .parse(date);
            } catch (Exception e) {
                return null;
            }
        }

    }
