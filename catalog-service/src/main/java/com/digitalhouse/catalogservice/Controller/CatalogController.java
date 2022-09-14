package com.digitalhouse.catalogservice.Controller;

import java.util.List;
import java.util.Objects;

import com.digitalhouse.catalogservice.Model.CatalogDto;
import com.digitalhouse.catalogservice.Service.ICatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/catalogs")
public class CatalogController {
    @Value("${server.port}")
    private String port;

    private ICatalogService catalogService;

    @Autowired
    public CatalogController(ICatalogService catalogService) {
        this.catalogService = catalogService;
    }

	@GetMapping("/db/{genre}")
	ResponseEntity<CatalogDto> getCatalogByGenreDB(@PathVariable String genre, HttpServletResponse response) {
		CatalogDto catalogDto = catalogService.getCatalogByGenreDB(genre);
		response.addHeader("port", port);
		return Objects.isNull(catalogDto)
				? new ResponseEntity<>(HttpStatus.NOT_FOUND)
				: new ResponseEntity<>(catalogDto, HttpStatus.OK);
	}

    @GetMapping("/{genre}")
    ResponseEntity<CatalogDto> getCatalogByGenre(@PathVariable String genre, HttpServletResponse response) {
        CatalogDto catalogDto = catalogService.getCatalogByGenreFeign(genre);
        response.addHeader("port", port);
        return Objects.isNull(catalogDto)
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(catalogDto, HttpStatus.OK);
    }

    //    Este endpoint se creo exclusivamente para probar el CircuitBreaker
    @GetMapping("/errors/{genre}")
    ResponseEntity<CatalogDto> getCatalogByGenreError(@PathVariable String genre, @RequestParam("movieErrors") Boolean movieErrors, @RequestParam("serieErrors") Boolean serieErrors, HttpServletResponse response) {
        CatalogDto catalogDto = catalogService.getCatalogByGenreFeignError(genre,movieErrors,serieErrors);
        response.addHeader("port", port);
        return Objects.isNull(catalogDto)
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(catalogDto, HttpStatus.OK);
    }
}
