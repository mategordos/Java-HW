package com.epam.training.ticketservice.core.movie.persistence.repository;

import com.epam.training.ticketservice.core.movie.persistence.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {

    @Transactional
    Optional<Movie> findMovieByMovieTitle(String movieTitle);

    @Transactional
    void deleteMovieByMovieTitle(String movieTitle);
}
