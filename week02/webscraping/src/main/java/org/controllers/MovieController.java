package org.controllers;

import org.persistence.MovieMapper;

public class MovieController {
    public void  getByRating(double rating){
        MovieMapper movieMapper = new MovieMapper();
        System.out.println(movieMapper.getRating(rating));
    }

    public void getSortedByReleaseDate(){

        MovieMapper movieMapper = new MovieMapper();
        System.out.println(movieMapper.getReleaseDate());


    }


}
