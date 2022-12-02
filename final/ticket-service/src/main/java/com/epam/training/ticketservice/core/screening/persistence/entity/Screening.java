package com.epam.training.ticketservice.core.screening.persistence.entity;

import com.epam.training.ticketservice.core.movie.persistence.entity.Movie;
import com.epam.training.ticketservice.core.room.persistence.entity.Room;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data
@NoArgsConstructor
public class Screening {

        @Id
        @GeneratedValue
        private long id;
        @ManyToOne
        private Movie movie;
        @ManyToOne
        private Room room;
        private Date screeningStartDate;

        public Screening(Movie movie, Room room, Date screeningStartDate){
            this.movie = movie;
            this.room = room;
            this.screeningStartDate = screeningStartDate;
        }
    }