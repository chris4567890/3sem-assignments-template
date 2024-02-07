package org.example;

import org.controllers.MovieController;
import org.entities.MovieDTO;
import org.persistence.MovieMapper;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {


        MovieMapper movieMapper = new MovieMapper();
        //MovieDTO movie = movieMapper.getMovie("tt5177120");
        //System.out.println(movie);
        //movieMapper.getReleaseDate();
        MovieController movieController = new MovieController();
        movieController.getByRating(1);
        //movieController.getByRating(1);
        //movieController.getSortedByReleaseDate();
    }
}