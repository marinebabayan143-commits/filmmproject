package org.example.filmmproject.config;

import org.example.filmmproject.models.Movie;
import org.example.filmmproject.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner loadMovies(MovieRepository movieRepository) {

        return args -> {

            if (movieRepository.count() > 0) {
                return;
            }

            movieRepository.save(
                    new Movie(
                            "The Dark Knight",
                            "Action",
                            2008,
                            9.0,
                            "Batman faces a criminal mastermind who brings chaos to Gotham City.",
                            false,
                            "the-dark-knight.webp"
                    )
            );

            movieRepository.save(
                    new Movie(
                            "Interstellar",
                            "Science Fiction",
                            2014,
                            8.7,
                            "Explorers travel through a wormhole in space in search of a new home for humanity.",
                            false,
                            "interstellar.jfif"
                    )
            );

            movieRepository.save(
                    new Movie(
                            "Inception",
                            "Science Fiction",
                            2010,
                            8.8,
                            "A skilled thief enters people's dreams to steal secrets and attempts an impossible mission.",
                            false,
                            "inception.jfif"
                    )
            );

            movieRepository.save(
                    new Movie(
                            "The Shawshank Redemption",
                            "Drama",
                            1994,
                            9.3,
                            "Two imprisoned men form a lasting friendship through years of hope and hardship.",
                            false,
                            "shawshank-redemption.jfif"
                    )
            );

            movieRepository.save(
                    new Movie(
                            "Gladiator",
                            "Action",
                            2000,
                            8.5,
                            "A former Roman general seeks justice after losing his family and freedom.",
                            false,
                            "gladiator.jfif"
                    )
            );

            movieRepository.save(
                    new Movie(
                            "The Matrix",
                            "Science Fiction",
                            1999,
                            8.7,
                            "A computer hacker discovers that the world he knows is not what it seems.",
                            false,
                            "the-matrix.jfif"
                    )
            );

            movieRepository.save(
                    new Movie(
                            "Parasite",
                            "Thriller",
                            2019,
                            8.5,
                            "A struggling family gradually becomes involved in the lives of a wealthy household.",
                            false,
                            "parasite.jfif"
                    )
            );

            movieRepository.save(
                    new Movie(
                            "Whiplash",
                            "Drama",
                            2014,
                            8.5,
                            "An ambitious young drummer faces an intense and demanding music instructor.",
                            false,
                            "whiplash.jfif"
                    )
            );

            movieRepository.save(
                    new Movie(
                            "Dune",
                            "Science Fiction",
                            2021,
                            8.0,
                            "A gifted young man travels to a dangerous desert world that holds the universe's most valuable resource.",
                            false,
                            "dune.jfif"
                    )
            );

            movieRepository.save(
                    new Movie(
                            "Oppenheimer",
                            "Drama",
                            2023,
                            8.3,
                            "The story explores the life of physicist J. Robert Oppenheimer and the creation of the atomic bomb.",
                            false,
                            "oppenheimer.jfif"
                    )
            );
        };
    }
}