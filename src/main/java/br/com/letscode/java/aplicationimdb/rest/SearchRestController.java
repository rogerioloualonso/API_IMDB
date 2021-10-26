package br.com.letscode.java.aplicationimdb.rest;

import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class SearchRestController {

    private final MovieMinimalRestRepository restRepository;

    public  SearchRestController(MovieMinimalRestRepository restRepository){
        this.restRepository = restRepository;
    }



    @GetMapping("/search")
    public ResultSearch search (@RequestParam  String movieTitle){

        String filepath = getFilePath("cache.csv");
        try (Stream<String> lines = Files.lines(Path.of(filepath))) {
            Stream<String> list = Files.lines(Path.of(filepath));

            if(getCountSearchCsv(movieTitle, lines) == 0){
                writeSearchCsv(movieTitle);
            }else{
                postResultSearch(movieTitle, list);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.restRepository.search(movieTitle);
    }

    private long getCountSearchCsv(String movieTitle, Stream<String> lines) {
        return lines
                .map(s-> Arrays.stream(s.split(";")).toArray())
                .filter(s->s[0].equals(movieTitle))
                .count();
    }

    private void postResultSearch(String movieTitle, Stream<String> list) {
        list
                .map(s-> Arrays.stream(s.split(";")).toArray())
                .filter(s->s[0].equals(movieTitle))
                .forEach(s-> Arrays.stream(s).collect(Collectors.toList()));
                //.forEach(s-> System.out.println(s[0]));

        //Criar endpoin para mostrar list
    }

    private void writeSearchCsv(String movieTitle) {
        ResultSearch busca = this.restRepository.search(movieTitle);

        writeCsv(movieTitle + ";");
        busca.getResultList()
                .stream()
                .forEach(s -> s.escreverCsv(s.toString() + ";"));
        writeCsv("\n");
    }

    private void writeCsv(String linha){
        String filePath = getFilePath("cache.csv");
        try{
            Files.writeString(Path.of(filePath), linha, StandardOpenOption.APPEND);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getFilePath(String fileName){
        URL url = getClass().getClassLoader().getResource(fileName);
        File file = new File(url.getFile());
        return file.getPath();
    }



}
