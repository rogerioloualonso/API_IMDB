package br.com.letscode.java.aplicationimdb.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchRestController {

    private final MovieMinimalRestRepository restRepository;

    public  SearchRestController(MovieMinimalRestRepository restRepository){
        this.restRepository = restRepository;
    }

    @GetMapping("/movies")
    public ResultSearch search (@RequestParam  String movieTitle){
        return this.restRepository.search(movieTitle);
    }

}
