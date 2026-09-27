package org.example.filmmproject.service;

import org.example.filmmproject.models.Movie;
import org.example.filmmproject.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }

    public List<Movie> getTopRatedMovies() {
        return movieRepository.findAllByOrderByRatingDesc();
    }

    public List<Movie> getFavoriteMovies() {
        return movieRepository.findByFavoriteTrueOrderByRatingDesc();
    }

    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    public void toggleFavorite(Long id) {

        Optional<Movie> optionalMovie = movieRepository.findById(id);

        if (optionalMovie.isPresent()) {

            Movie movie = optionalMovie.get();

            movie.setFavorite(!movie.isFavorite());

            movieRepository.save(movie);
        }
    }

    public List<Movie> filterMovies(
            String search,
            String genre,
            Integer year,
            Double minRating,
            String sort) {

        List<Movie> allMovies = movieRepository.findAll();

        List<Movie> filteredMovies = new ArrayList<>();

        for (Movie movie : allMovies) {

            boolean matchesSearch =
                    search == null ||
                            search.isBlank() ||
                            movie.getTitle()
                                    .toLowerCase()
                                    .contains(search.toLowerCase());

            boolean matchesGenre =
                    genre == null ||
                            genre.isBlank() ||
                            movie.getGenre().equalsIgnoreCase(genre);

            boolean matchesYear =
                    year == null ||
                            movie.getYear() == year;

            boolean matchesRating =
                    minRating == null ||
                            movie.getRating() >= minRating;

            if (matchesSearch &&
                    matchesGenre &&
                    matchesYear &&
                    matchesRating) {

                filteredMovies.add(movie);
            }
        }

        if ("rating".equals(sort)) {

            filteredMovies.sort(
                    Comparator.comparingDouble(Movie::getRating)
                            .reversed()
            );

        } else if ("year".equals(sort)) {

            filteredMovies.sort(
                    Comparator.comparingInt(Movie::getYear)
                            .reversed()
            );

        } else if ("title".equals(sort)) {

            filteredMovies.sort(
                    Comparator.comparing(
                            Movie::getTitle,
                            String.CASE_INSENSITIVE_ORDER
                    )
            );
        }

        return filteredMovies;
    }
}