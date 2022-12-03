package com.epam.training.ticketservice.core.screening.persistence.entity;

import com.epam.training.ticketservice.core.movie.persistence.entity.Movie;
import com.epam.training.ticketservice.core.room.persistence.entity.Room;
import lombok.Setter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Screening {

    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private Movie movie;
    @ManyToOne
    private Room room;
    @Temporal(TemporalType.TIMESTAMP)
    private Date screeningStartDate;

    public Screening(Movie movie, Room room, Date screeningStartDate) {
        this.movie = movie;
        this.room = room;
        this.screeningStartDate = screeningStartDate;
    }
}
