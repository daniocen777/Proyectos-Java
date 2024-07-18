package com.danicode.movie_rental.domain.dto.response;

public class MovieResponse {

    private Long movieId;
    private String title;
    private Integer duration;
    private Integer rating;
    private ProducerResponse producer;

    public MovieResponse() {
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public ProducerResponse getProducer() {
        return producer;
    }

    public void setProducer(ProducerResponse producer) {
        this.producer = producer;
    }
}
