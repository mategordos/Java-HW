package com.epam.training.ticketservice.core.movie.persistence.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue
    private long id;
    @Column(unique = true)
    private String movieTitle;
    private String movieGenre;
    private Integer movieLength;

    public Movie(String movieTitle, String movieGenre, Integer movieLength){
        this.movieTitle = movieTitle;
        this.movieGenre = movieGenre;
        this.movieLength = movieLength;
    }

}
