package fx;

import data.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import utils.FileUtils;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the Movie Management Window
 * @author Fernando Tortosa Lopez
 */
public class MovieController implements Initializable {
    @FXML
    private Button btnClear;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnEdit;
    @FXML
    private TextField txtTitle;
    @FXML
    private TextField txtDirector;
    @FXML
    private TextField txtYear;
    @FXML
    private Button btnAdd;
    @FXML
    private TableView<Movie> tblMovies;
    @FXML
    private TableColumn colTitle;
    @FXML
    private TableColumn colDirector;
    @FXML
    private TableColumn colYear;
    private ObservableList<Movie> movies;

    /**
     * Initializes the UI and loads from file if exists
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (new File("./src/data/movies.dat").exists())
            movies = FileUtils.loadMovies();
        else
            movies = FXCollections.observableArrayList();

        this.colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        this.colDirector.setCellValueFactory(new PropertyValueFactory<>("director"));
        this.colYear.setCellValueFactory(new PropertyValueFactory<>("releaseYear"));

        this.tblMovies.setItems(movies);
    }

    /**
     * When user clicks a non-empty field of the table
     * the object associated gets selected and
     * printed to textFields
     * @param mouseEvent
     */
    public void tableClick(MouseEvent mouseEvent) {
        Movie selected = this.tblMovies.getSelectionModel().getSelectedItem();
        if (selected != null){
            this.txtDirector.setText(selected.getDirector());
            this.txtTitle.setText(selected.getTitle());
            this.txtYear.setText(Integer.toString(selected.getReleaseYear()));
        }
    }

    /**
     * If there is text in the textFields this method tries
     * to add it to the list if it doesn't exist already there
     * @param actionEvent
     */
    public void addMovie(ActionEvent actionEvent) {
        if (!fieldsAreEmpty()){
            Movie m = new Movie(this.txtTitle.getText(), this.txtDirector.getText(), Integer.parseInt(this.txtYear.getText()));
            if (!movies.contains(m)){
                movies.add(m);
                FileUtils.saveMovies(movies);
            }
            this.tblMovies.refresh();
        }
    }
    private boolean fieldsAreEmpty(){
        return (this.txtDirector.getText().equals("") && this.txtTitle.getText().equals("") && this.txtYear.getText().equals(""));
    }

    /**
     * If an object is selected this method reads from the text fields and
     * updates the corresponding object
     * @param actionEvent
     */
    public void editMovie(ActionEvent actionEvent) {
        Movie selected = this.tblMovies.getSelectionModel().getSelectedItem();
        if (selected == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("You must select movie first");
            alert.showAndWait();
        }
        else{
            String title = this.txtTitle.getText();
            String director = this.txtDirector.getText();
            try{
                int releaseYear = Integer.parseInt(this.txtYear.getText());
                Movie aux = new Movie(title, director, releaseYear);
                if (!movies.contains(aux)){
                    selected.setTitle(aux.getTitle());
                    selected.setDirector(aux.getDirector());
                    selected.setReleaseYear(aux.getReleaseYear());
                    this.tblMovies.refresh();
                }
                FileUtils.saveMovies(movies);
            }catch (NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Invalid year");
                alert.showAndWait();
            }
        }
    }

    /**
     * If an object is selected this method removes it from the table
     * @param actionEvent
     */
    public void deleteMovie(ActionEvent actionEvent) {
        Movie selected = this.tblMovies.getSelectionModel().getSelectedItem();
        if (selected == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("You must select a movie first");
            alert.showAndWait();
        }
        else{
            movies.remove(selected);
            FileUtils.saveMovies(movies);
            this.tblMovies.refresh();
        }
    }

    /**
     * This method sets all textFields text property
     * to an empty string
     * and deselects the item from the table
     * @param actionEvent
     */
    public void clearSelection(ActionEvent actionEvent) {
        this.txtYear.setText("");
        this.txtTitle.setText("");
        this.txtDirector.setText("");
        this.tblMovies.getSelectionModel().clearSelection();
    }
}
