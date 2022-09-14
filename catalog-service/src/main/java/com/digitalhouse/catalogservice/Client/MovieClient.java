package com.digitalhouse.catalogservice.Client;

import java.util.List;
import com.digitalhouse.catalogservice.Model.Document.Movie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "movie-service")
public interface MovieClient {
    @GetMapping("/movies/{genre}")
    ResponseEntity<List<Movie>> findMovieByGenre(@PathVariable("genre") String genre);

    //    Esta request se creo exclusivamente para probar el CircuitBreaker
    @GetMapping("/movies/withErrors/{genre}")
    ResponseEntity<List<Movie>> findMovieByGenreError(@PathVariable(value = "genre") String genre,
                                                      @RequestParam("throwError") boolean throwError);
}
