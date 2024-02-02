package org.app;

public class Book {

    private String author;
    private String title;

    private int release_year;

    private int pages;

    private double rating;

    public Book(String author,String title,int release_year,int pages, double rating){

        this.author = author;
        this.title = title;
        this.release_year = release_year;
        this.pages = pages;
        this.rating = rating;

    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRelease_year() {
        return release_year;
    }

    public void setRelease_year(int release_year) {
        this.release_year = release_year;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", release_year=" + release_year +
                ", pages=" + pages +
                ", rating=" + rating +
                '}';
    }
}
