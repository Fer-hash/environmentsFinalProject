package data;

import java.util.Objects;

/**
 * Class to define music albums and their attributes
 * @author Fernando Tortosa Lopez
 */
public class MusicAlbum {
    private String title;
    private String author;
    private int releaseYear;

    public MusicAlbum() {
    }

    /**
     * Default constructor with all parameters
     * @param title String with the title
     * @param author String with the artist who recorded the album
     * @param releaseYear Integer specifying album's release date
     */
    public MusicAlbum(String title, String author, int releaseYear) {
        this.title = title;
        this.author = author;
        this.releaseYear = releaseYear;
    }

    /**
     * Getter for the title
     * @return String with title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for the title
     * @param title String with the new title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for the author
     * @return String with the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Setter for the author
     * @param author a String with the new author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Getter for release year
     * @return Integer with release year
     */
    public int getReleaseYear() {
        return releaseYear;
    }

    /**
     * Setter for release year
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
        return this.title + ";" + this.author + ";" + this.releaseYear;
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
        MusicAlbum that = (MusicAlbum) o;
        return releaseYear == that.releaseYear && Objects.equals(title, that.title) && Objects.equals(author, that.author);
    }

    /**
     * Override of hashcode method
     * @return an integer representing the current instance
     */
    @Override
    public int hashCode() {
        return Objects.hash(title, author, releaseYear);
    }
}
