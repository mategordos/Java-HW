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
    import static org.mockito.Mockito.never;

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
        private static final Date DATE4 = convertStringToDate("2021-03-15 12:00");
        private static final Date DATE5 = convertStringToDate("2021-03-15 09:07");
        private static final Screening newScreening = new Screening(MOVIE, ROOM, DATE);
        private static final Screening earlierScreening = new Screening(MOVIE, ROOM, DATE3);
        private static final Screening laterScreening = new Screening(MOVIE, ROOM, DATE4);
        private static final Screening earlierScreeningWithGap = new Screening(MOVIE, ROOM, DATE5);
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
        void testCreateScreening_noOtherScreening() {
            // Given
            // no preexisting screenings
            Mockito.when(screeningRepository.findByRoom_RoomName(ROOM.getRoomName())).thenReturn(List.of());

            // valid/existing room
            Mockito.when(roomRepository.findRoomByRoomName(ROOM.getRoomName())).thenReturn(Optional.of(ROOM));
            // valid/existing movie
            Mockito.when(movieRepository.findMovieByMovieTitle(MOVIE.getMovieTitle())).thenReturn(Optional.of(MOVIE));
            // can save entity
            Mockito.when(screeningRepository.save(newScreening)).thenReturn(newScreening);

            // When
            underTest.createScreening(DTO);

            // Then
            Mockito.verify(screeningRepository).save(newScreening);
        }

        @Test
        void testCreateScreening_overlapWithEarlierScreening() {
            // Given
            // there's a screening that will overlap
            Mockito.when(screeningRepository.findByRoom_RoomName(ROOM.getRoomName())).thenReturn(List.of(
                earlierScreening
            ));

            // valid/existing room
            Mockito.when(roomRepository.findRoomByRoomName(ROOM.getRoomName())).thenReturn(Optional.of(ROOM));
            // valid/existing movie
            Mockito.when(movieRepository.findMovieByMovieTitle(MOVIE.getMovieTitle())).thenReturn(Optional.of(MOVIE));

            // When
            underTest.createScreening(DTO);

            // Then
            Mockito.verify(screeningRepository, never()).save(newScreening);
        }

        @Test
        void testCreateScreening_overlapWith10MinutesGap() {
            // Given
            // there's a screening that will overlap
            Mockito.when(screeningRepository.findByRoom_RoomName(ROOM.getRoomName())).thenReturn(List.of(
                earlierScreeningWithGap
            ));

            // valid/existing room
            Mockito.when(roomRepository.findRoomByRoomName(ROOM.getRoomName())).thenReturn(Optional.of(ROOM));
            // valid/existing movie
            Mockito.when(movieRepository.findMovieByMovieTitle(MOVIE.getMovieTitle())).thenReturn(Optional.of(MOVIE));

            // When
            underTest.createScreening(DTO);

            // Then
            Mockito.verify(screeningRepository, never()).save(newScreening);
        }

        @Test
        void testCreateScreening_overlapWithLaterScreening() {
            // Given
            // there's a screening that will overlap
            Mockito.when(screeningRepository.findByRoom_RoomName(ROOM.getRoomName())).thenReturn(List.of(
                laterScreening
            ));

            // valid/existing room
            Mockito.when(roomRepository.findRoomByRoomName(ROOM.getRoomName())).thenReturn(Optional.of(ROOM));
            // valid/existing movie
            Mockito.when(movieRepository.findMovieByMovieTitle(MOVIE.getMovieTitle())).thenReturn(Optional.of(MOVIE));

            // When
            underTest.createScreening(DTO);

            // Then
            Mockito.verify(screeningRepository, never()).save(newScreening);
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
