package org.example.filmmproject.controllers;

import org.example.filmmproject.models.Movie;
import org.example.filmmproject.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    // =========================
    // HOME
    // =========================

    @GetMapping("/")
    public String home(
            @RequestParam(required = false, defaultValue = "") String search,
            Model model) {

        List<Movie> movies;

        if (search.isBlank()) {

            movies = movieService.getAllMovies();

        } else {

            movies = movieService.filterMovies(
                    search,
                    "",
                    null,
                    null,
                    "rating"
            );
        }

        model.addAttribute("movies", movies);
        model.addAttribute("search", search);

        return "index";
    }


    // =========================
    // MOVIES
    // =========================

    @GetMapping("/movies")
    public String movies(
            @RequestParam(required = false, defaultValue = "")
            String search,

            @RequestParam(required = false, defaultValue = "")
            String genre,

            @RequestParam(required = false)
            Integer year,

            @RequestParam(required = false)
            Double minRating,

            @RequestParam(required = false, defaultValue = "")
            String sort,

            Model model) {

        List<Movie> movies =
                movieService.filterMovies(
                        search,
                        genre,
                        year,
                        minRating,
                        sort
                );

        model.addAttribute("movies", movies);

        model.addAttribute("search", search);
        model.addAttribute("genre", genre);
        model.addAttribute("year", year);
        model.addAttribute("minRating", minRating);
        model.addAttribute("sort", sort);

        return "movies";
    }


    // =========================
    // MOVIE DETAILS
    // =========================

    @GetMapping("/movies/{id}")
    public String movieDetails(
            @PathVariable Long id,
            Model model) {

        Optional<Movie> optionalMovie =
                movieService.getMovieById(id);

        if (optionalMovie.isEmpty()) {
            return "redirect:/movies";
        }

        model.addAttribute(
                "movie",
                optionalMovie.get()
        );

        return "movie-details";
    }


    // =========================
    // TOP RATED
    // =========================

    @GetMapping("/top-rated")
    public String topRated(Model model) {

        List<Movie> movies =
                movieService.getTopRatedMovies();

        model.addAttribute("movies", movies);

        return "top-rated";
    }


    // =========================
    // FAVORITES
    // =========================

    @GetMapping("/favorites")
    public String favorites(Model model) {

        List<Movie> movies =
                movieService.getFavoriteMovies();

        model.addAttribute("movies", movies);

        return "favorites";
    }


    // =========================
    // FAVORITE
    // =========================

    @PostMapping("/movies/{id}/favorite")
    public String toggleFavorite(
            @PathVariable Long id,
            @RequestParam(required = false, defaultValue = "/movies")
            String redirect) {

        movieService.toggleFavorite(id);

        if (!redirect.startsWith("/")) {
            redirect = "/movies";
        }

        return "redirect:" + redirect;
    }


    // =========================
    // ADD
    // =========================

    @GetMapping("/movies/add")
    public String addMoviePage(Model model) {

        model.addAttribute(
                "movie",
                new Movie()
        );

        return "add";
    }


    @PostMapping("/movies/add")
    public String addMovie(
            @ModelAttribute Movie movie) {

        movieService.saveMovie(movie);

        return "redirect:/movies";
    }


    // =========================
    // EDIT
    // =========================

    @GetMapping("/movies/edit/{id}")
    public String editMovie(
            @PathVariable Long id,
            Model model) {

        Optional<Movie> optionalMovie =
                movieService.getMovieById(id);

        if (optionalMovie.isEmpty()) {
            return "redirect:/movies";
        }

        model.addAttribute(
                "movie",
                optionalMovie.get()
        );

        return "edit";
    }


    @PostMapping("/movies/update/{id}")
    public String updateMovie(
            @PathVariable Long id,
            @ModelAttribute Movie movie) {

        movie.setId(id);

        movieService.saveMovie(movie);

        return "redirect:/movies";
    }


    // =========================
    // DELETE
    // =========================

    @PostMapping("/movies/delete/{id}")
    public String deleteMovie(
            @PathVariable Long id) {

        movieService.deleteMovie(id);

        return "redirect:/movies";
    }

    @GetMapping("/about")
    public String about(){

        return "about";
    }
}