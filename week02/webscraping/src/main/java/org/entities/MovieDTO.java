package org.entities;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MovieDTO {
    private int id;

    private String imdb_id;
    private String original_title;

    private String overview;
    private String  release_date;
    private double vote_average;

    public String release_year(){
        String year;
        LocalDate localDate = LocalDate.parse(release_date);
        year = Integer.toString(localDate.getYear());
        return year;
    }

    public LocalDate localDateFromString(){
        //DateTimeFormatter the_converter = DateTimeFormatter.ofPattern(release_date);
        LocalDate localDate = LocalDate.parse(release_date);
        return localDate;

    }



}
