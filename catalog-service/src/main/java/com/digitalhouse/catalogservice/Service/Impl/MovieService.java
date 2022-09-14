package com.digitalhouse.catalogservice.Service.Impl;

import com.digitalhouse.catalogservice.Client.MovieClient;
import com.digitalhouse.catalogservice.Model.Document.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    public static Logger LOG = LoggerFactory.getLogger(MovieService.class);

    private final MovieClient movieClient;

    @Autowired
    public MovieService(MovieClient movieFeignClient) {
        this.movieClient = movieFeignClient;
    }

    public List<Movie>  findMovieByGenre(String genere){
        ResponseEntity<List<Movie>> movieResponse = movieClient.findMovieByGenre(genere);
        LOG.info("Puerto de la Instancia de Movie-Service es: " + movieResponse.getHeaders().get("port"));
        if (movieResponse.getStatusCode().is2xxSuccessful()){
            return movieResponse.getBody();
        }
        return null;
    }

    public List<Movie> findMovieByGenreError(String genere, Boolean movieErrors){
        ResponseEntity<List<Movie>> movieResponse = movieClient.findMovieByGenreError(genere, movieErrors);
        LOG.info("Puerto de la Instancia de Movie-Service es: " + movieResponse.getHeaders().get("port"));
        if (movieResponse.getStatusCode().is2xxSuccessful()){
            return movieResponse.getBody();
        }
        return null;
    }
}
