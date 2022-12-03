package com.epam.training.ticketservice.core.movie;

import com.epam.training.ticketservice.core.movie.model.MovieDto;
import com.epam.training.ticketservice.core.movie.persistence.entity.Movie;
import com.epam.training.ticketservice.core.movie.persistence.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public List<MovieDto> getMovieList() {
        return movieRepository.findAll().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }


    @Override
    public void createMovie(MovieDto movieDto) {
        Movie movie = new Movie(movieDto.getMovieTitle(),
                movieDto.getMovieGenre(),
                movieDto.getMovieLength());
        movieRepository.save(movie);
    }

    @Override
    public void updateMovie(MovieDto movieDto) {
        Optional<Movie> movie = movieRepository.findMovieByMovieTitle(movieDto.getMovieTitle());
        if (movie.isPresent()) {
            movie.get().setMovieGenre(movieDto.getMovieGenre());
            movie.get().setMovieLength(movieDto.getMovieLength());
            movieRepository.save(movie.get());
        } else {
            throw new IllegalArgumentException("Movie does not exist!");
        }
    }

    @Override
    public void deleteMovie(String movieTitle) {
        movieRepository.deleteMovieByMovieTitle(movieTitle);
    }

    private MovieDto convertEntityToDto(Movie movie) {
        return  MovieDto.builder()
                .movieTitle(movie.getMovieTitle())
                .movieGenre(movie.getMovieGenre())
                .movieLength(movie.getMovieLength())
                .build();
    }


    private Optional<MovieDto> convertEntityToDto(Optional<Movie> movie) {
        return movie.isEmpty() ? Optional.empty() : Optional.of(convertEntityToDto(movie.get()));
    }
}

