package com.danicode.movie_rental.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.EmbeddedId;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.JoinColumn;

import java.time.LocalDateTime;

@Entity
@Table(name = "customers_movies")
public class CustomerMovieEntity {

    @EmbeddedId
    private CustomerMoviePK id;

    @Column(name = "date_rented")
    private LocalDateTime dateRented;


    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @ManyToOne
    @MapsId("customerId")
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "movie_id", insertable = false, updatable = false)
    private MovieEntity movie;

    public CustomerMovieEntity() {
    }

    public CustomerMoviePK getId() {
        return id;
    }

    public void setId(CustomerMoviePK id) {
        this.id = id;
    }

    public LocalDateTime getDateRented() {
        return dateRented;
    }

    public void setDateRented(LocalDateTime dateRented) {
        this.dateRented = dateRented;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public MovieEntity getMovie() {
        return movie;
    }

    public void setMovie(MovieEntity movie) {
        this.movie = movie;
    }
}
