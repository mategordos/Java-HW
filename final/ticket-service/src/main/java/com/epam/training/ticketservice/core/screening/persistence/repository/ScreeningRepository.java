package com.epam.training.ticketservice.core.screening.persistence.repository;

import com.epam.training.ticketservice.core.room.persistence.entity.Room;
import com.epam.training.ticketservice.core.screening.persistence.entity.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, String> {
    @Transactional
    void deleteScreeningByScreeningDto();
}
