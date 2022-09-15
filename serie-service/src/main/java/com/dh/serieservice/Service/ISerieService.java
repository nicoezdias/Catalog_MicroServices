package com.dh.serieservice.Service;

import com.dh.serieservice.Model.Serie;

import java.util.List;

public interface ISerieService {
    List<Serie> getListByGenre(String genre);
    List<Serie> getListByGenreError(String genre, Boolean throwError);
    void save(Serie serie);
}
