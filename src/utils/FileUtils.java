package utils;

import data.Movie;
import data.MusicAlbum;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;

/**
 * This class is used in order to serialize both Movies and Music Albums into plain text .dat files
 * @author Fernando Tortosa Lopez
 */
public class FileUtils {
    /**
     * Static method to write serialized Music Albums to a file
     * @param albums An ObservableList of MusicAlbums containing the list of albums to save
     */
    public static void saveAlbums(ObservableList<MusicAlbum> albums){
        try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter("./src/data/albums.dat", false)))) {
            for (MusicAlbum album : albums) {
                printWriter.println(album.toFile());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Static method to load serialized information from a file into an ObservableList
     * @return an ObservableList of MusicAlbums containing all albums in the file
     */
    public static ObservableList<MusicAlbum> loadAlbums(){
        ObservableList<MusicAlbum> albums = FXCollections.observableArrayList();
        try{
            BufferedReader file = new BufferedReader(new FileReader("./src/data/albums.dat"));
            String line = file.readLine();
            while (line != null){
                String[] data = line.split(";");
                MusicAlbum album = new MusicAlbum(data[0], data[1], Integer.parseInt(data[2]));
                albums.add(album);
                line = file.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return albums;
    }
    /**
     * Static method to write serialized Movies to a file
     * @param movies An ObservableList of Movies containing the list of movies to save
     */
    public static void saveMovies(ObservableList<Movie> movies){
        try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter("./src/data/movies.dat", false)))) {
            for (Movie movie : movies) {
                printWriter.println(movie.toFile());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Static method to load serialized information from a file into an ObservableList
     * @return an ObservableList of Movies containing all movies in the file
     */
    public static ObservableList<Movie> loadMovies(){
        ObservableList<Movie> movies = FXCollections.observableArrayList();
        try{
            BufferedReader file = new BufferedReader(new FileReader("./src/data/movies.dat"));
            String line = file.readLine();
            while (line != null){
                String[] data = line.split(";");
                Movie movie = new Movie(data[0], data[1], Integer.parseInt(data[2]));
                movies.add(movie);
                line = file.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movies;
    }
}
