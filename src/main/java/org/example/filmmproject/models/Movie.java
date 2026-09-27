package org.example.filmmproject.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Movie {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String genre;

    private int year;

    private double rating;

    private String description;

    private boolean favorite;

    private String imageUrl;


    public Movie() {
    }


    public Movie(String title, String genre, int year,
                 double rating, String description,
                 boolean favorite, String imageUrl) {

        this.title = title;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
        this.description = description;
        this.favorite = favorite;
        this.imageUrl = imageUrl;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getGenre() {
        return genre;
    }


    public void setGenre(String genre) {
        this.genre = genre;
    }


    public int getYear() {
        return year;
    }


    public void setYear(int year) {
        this.year = year;
    }


    public double getRating() {
        return rating;
    }


    public void setRating(double rating) {
        this.rating = rating;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public boolean isFavorite() {
        return favorite;
    }


    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }


    public String getImageUrl() {
        return imageUrl;
    }


    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


}
