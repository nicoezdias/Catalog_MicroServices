package com.digitalhouse.catalogservice.Service;

import com.digitalhouse.catalogservice.Model.CatalogDto;

public interface ICatalogService {
    CatalogDto getCatalogByGenreDB(String genere);
    CatalogDto getCatalogByGenreFeign(String genere);
    CatalogDto getCatalogByGenreFeignError(String genre, Boolean movieErrors, Boolean serieErrors);
}
