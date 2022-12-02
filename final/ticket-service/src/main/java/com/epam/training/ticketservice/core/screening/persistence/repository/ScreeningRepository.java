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
import java.util.List;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, String> {

    @Transactional
    @Modifying
    @Query("delete from Screening s where s.movie = :#{dto.movieTitle} and"
            + " s.room = :#{dto.roomName} and s.screeningStartDate = :#{dto.screeningStartDate}")
    void deleteScreeningByScreeningDto(@Param("dto")ScreeningDto dto);
}
