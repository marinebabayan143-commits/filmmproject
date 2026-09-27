package org.example.filmmproject.repository;

import org.example.filmmproject.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByTitleContainingIgnoreCase(String title);

    List<Movie> findByGenreIgnoreCase(String genre);

    List<Movie> findByRatingGreaterThanEqual(double rating);

    List<Movie> findByFavoriteTrue();

    List<Movie> findAllByOrderByRatingDesc();

    List<Movie> findByFavoriteTrueOrderByRatingDesc();
}