package com.epam.training.ticketservice.core.screening.persistence.repository;

import com.epam.training.ticketservice.core.room.persistence.entity.Room;
import com.epam.training.ticketservice.core.screening.model.ScreeningDto;
import com.epam.training.ticketservice.core.screening.persistence.entity.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Transactional
@Repository
public interface ScreeningRepository extends JpaRepository<Screening, String> {
    void deleteByMovie_MovieTitleLikeAndRoom_RoomNameLikeAndScreeningStartDate(String movieTitle,
                                                                               String roomName, Date screeningStartDate);

    List<Screening> findByScreeningStartDateAfterAndRoom_RoomName(Date screeningStartDate, String roomName);

    List<Screening> findByRoom_RoomName(String roomName);




}
