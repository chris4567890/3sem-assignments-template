package org.persistence;

import com.google.gson.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.entities.MovieDTO;

import java.io.Closeable;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;

public class MovieMapper implements Serializable {



    String fileName = "movieDb.ser";
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public String getResponeBody(String url){
        url = url;
        OkHttpClient client =new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder().url(url).get().addHeader("accept","application/json").addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3MDk2ZTRjOTNhNWI4OWFkZmU4YzNjYjVkZjdlZWZkMSIsInN1YiI6IjY1YzIwNGVhZmM1ZjA2MDE2OGM0ZmE0OSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.QDjQWRSu92Lw4QAWIAg7UwJ3QMhs-iCcJiKcosVBr-0")
                .build();


        try{
            Response response = client.newCall(request).execute();



            String res = response.body().string();
            System.out.println();
            return res;

        }catch (IOException e){
            throw new RuntimeException(e);
        }



    }

    public String getRating(double average_rating){
        OkHttpClient client = new OkHttpClient();
        String url = "https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=en-US&page=1&sort_by=popularity.desc&vote_average.gte={average_rating}".replace("{average_rating}",Double.toString(average_rating));

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3MDk2ZTRjOTNhNWI4OWFkZmU4YzNjYjVkZjdlZWZkMSIsInN1YiI6IjY1YzIwNGVhZmM1ZjA2MDE2OGM0ZmE0OSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.QDjQWRSu92Lw4QAWIAg7UwJ3QMhs-iCcJiKcosVBr-0")
                .build();
        try {
            Response response = client.newCall(request).execute();
            String result = getResponeBody(url);
            return result;
        }catch (IOException e){
            throw new RuntimeException(e);
        }

    }

    public String getReleaseDate(){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=en-US&page=1&sort_by=primary_release_date.asc")
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3MDk2ZTRjOTNhNWI4OWFkZmU4YzNjYjVkZjdlZWZkMSIsInN1YiI6IjY1YzIwNGVhZmM1ZjA2MDE2OGM0ZmE0OSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.QDjQWRSu92Lw4QAWIAg7UwJ3QMhs-iCcJiKcosVBr-0")
                .build();
        try{
            Response response = client.newCall(request).execute();
            String result = getResponeBody("https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=en-US&page=1&sort_by=vote_average.desc");
            return result;
        }catch (IOException e){
            throw new RuntimeException(e);
        }


    }

    public MovieDTO getMovie(String id){
        OkHttpClient client = new OkHttpClient();
        String url = "https://api.themoviedb.org/3/movie/{id}?language=en-US".replace("{id}",String.valueOf(id));

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("accept","application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3MDk2ZTRjOTNhNWI4OWFkZmU4YzNjYjVkZjdlZWZkMSIsInN1YiI6IjY1YzIwNGVhZmM1ZjA2MDE2OGM0ZmE0OSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.QDjQWRSu92Lw4QAWIAg7UwJ3QMhs-iCcJiKcosVBr-0")

                .build();
        try {
            Response response = client.newCall(request).execute();
            String result = getResponeBody(url);
            System.out.println(result);
            MovieDTO movie = gson.fromJson(result, MovieDTO.class);
            System.out.println();
            return movie;
        }catch(IOException e){
            throw new RuntimeException("you are a failure the lannisters sends their regards");
        }

    }


    /*public HashMap<MovieDTO, String> getMovieMap() {
        return movieMap;
    }

    public void setMovieMap(HashMap<MovieDTO, String> movieMap) {
        this.movieMap = movieMap;
    }*/
}
