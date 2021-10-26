package br.com.letscode.java.aplicationimdb.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class MovieMinimal {

    @JsonProperty("imdbID")
    private String imdbId;
    @JsonProperty("Title")
    private String title;
    private Integer year;

    @JsonProperty("Year")
    public void setYear(String year) {
        this.year = convertYear(year);
    }

    private int convertYear(final String year) {
        if (year.matches("\\d+")) {
            return Integer.parseInt(year);
        }
        return Arrays.stream(year.split("\\D"))
                .map(Integer::parseInt)
                .findFirst()
                .orElseThrow();
    }

    public static void escreverCsv(String linha){
        String filePath = getFilePath("cache.csv");
        try{
            Files.writeString(Path.of(filePath), linha, StandardOpenOption.APPEND);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getFilePath(String fileName){
        URL url = MovieMinimal.class.getClassLoader().getResource(fileName);
        File file = new File(url.getFile());
        return file.getPath();
    }


}