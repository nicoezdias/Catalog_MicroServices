package com.dh.serieservice.Controller;

import com.dh.serieservice.Model.Serie;
import com.dh.serieservice.Service.ISerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/series")
public class SerieController {

    @Value("${server.port}")
    private String port;

    private final ISerieService serieService;

    @Autowired
    public SerieController(ISerieService serieService) {
        this.serieService = serieService;
    }

    @GetMapping("/{genre}")
    public ResponseEntity<List<Serie>> getSerieByGenre(@PathVariable String genre, HttpServletResponse response) {
        response.addHeader("port", port);
        return ResponseEntity.ok().body(serieService.getListByGenre(genre));
    }

    //    Este endpoint se creo exclusivamente para probar el CircuitBreaker
    @GetMapping("/withErrors/{genre}")
    public ResponseEntity<List<Serie>> getSerieByGenreError(@PathVariable String genre, @RequestParam("throwError") boolean throwError, HttpServletResponse response) {
        response.addHeader("port", port);
        return ResponseEntity.ok().body(serieService.getListByGenreError(genre, throwError));
    }

    @PostMapping
    public ResponseEntity<String> saveSerie(@RequestBody Serie serie) {
        serieService.save(serie);
        return ResponseEntity.ok().body("La Serie '"+serie.getName()+"' se registro correctamente");
    }
}
