package com.digitalhouse.catalogservice.Service.Impl;

import com.digitalhouse.catalogservice.Client.SerieClient;
import com.digitalhouse.catalogservice.Model.Document.Serie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerieService {

    public static Logger LOG = LoggerFactory.getLogger(MovieService.class);

    private final SerieClient serieClient;

    @Autowired
    public SerieService(SerieClient serieFeignClient) {
        this.serieClient = serieFeignClient;
    }

    public List<Serie> findSerieByGenre(String genere){
        ResponseEntity<List<Serie>> serieResponse = serieClient.findSerieByGenre(genere);
        LOG.info("Puerto de la Instancia de Serie-Service es: " + serieResponse.getHeaders().get("port"));
        if (serieResponse.getStatusCode().is2xxSuccessful()){
            return serieResponse.getBody();
        }
        return null;
    }

    public List<Serie> findSerieByGenreError(String genere, Boolean serieErrors){
        ResponseEntity<List<Serie>> serieResponse = serieClient.findSerieByGenreError(genere,serieErrors);
        LOG.info("Puerto de la Instancia de Serie-Service es: " + serieResponse.getHeaders().get("port"));
        if (serieResponse.getStatusCode().is2xxSuccessful()){
            return serieResponse.getBody();
        }
        return null;
    }
}
