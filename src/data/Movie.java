package data;

import java.util.Objects;

/**
 * Class to define Movies and their attributes
 * @author Fernando Tortosa Lopez
 */

public class Movie {
    private String title;
    private String director;
    private int releaseYear;

    public Movie() {
    }

    /**
     * Default constructor with all parameters
     * @param title, a String for the movie's title
     * @param director, a String the movies director
     * @param releaseYear, an Integer for the release year for the movie
     */
    public Movie(String title, String director, int releaseYear) {
        this.title = title;
        this.director = director;
        this.releaseYear = releaseYear;
    }

    /**
     * Getter for the title
     * @return a String with the Movie's title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for the title
     * @param title , a String with the new title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for movie's director
     * @return a String with the movie's director
     */
    public String getDirector() {
        return director;
    }

    /**
     * Setter for the director
     * @param director, a String with new director
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * Getter for the release Year
     * @return Integer with movie's release year
     */
    public int getReleaseYear() {
        return releaseYear;
    }

    /**
     * Setter for the release year
     * @param releaseYear Integer with new release year
     */
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     * Method used to serialize the class to a plain text file
     * @return a String with serialized information about the class
     */
    public String toFile(){
        return this.title + ";" + this.director + ";" + this.releaseYear;
    }

    /**
     * Equals method override
     * @param o object to compare to
     * @return true if they are equal or false if they're not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return releaseYear == movie.releaseYear && Objects.equals(title, movie.title) && Objects.equals(director, movie.director);
    }

    /**
     * Override of hashcode method
     * @return an integer representing the current instance
     */
    @Override
    public int hashCode() {
        return Objects.hash(title, director, releaseYear);
    }
}
