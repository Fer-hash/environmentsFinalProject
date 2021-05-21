package fx;

import data.MusicAlbum;
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
 * Controller for the Music Management Window
 * @author Fernando Tortosa Lopez
 */
public class MusicController implements Initializable {
    @FXML
    private  Button btnClear;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnEdit;
    @FXML
    private TextField txtTitle;
    @FXML
    private TextField txtArtist;
    @FXML
    private TextField txtYear;
    @FXML
    private Button btnAdd;
    @FXML
    private TableView<MusicAlbum> tblMusic;
    @FXML
    private TableColumn colTitle;
    @FXML
    private TableColumn colArtist;
    @FXML
    private TableColumn colYear;
    private ObservableList<MusicAlbum> albums;

    /**
     * Initializes the UI and loads from file if exists
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (new File("./src/data/albums.dat").exists())
            albums = FileUtils.loadAlbums();
        else
            albums = FXCollections.observableArrayList();

        this.colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        this.colArtist.setCellValueFactory(new PropertyValueFactory<>("author"));
        this.colYear.setCellValueFactory(new PropertyValueFactory<>("releaseYear"));

        this.tblMusic.setItems(albums);
    }

    /**
     * If there is text in the textFields this method tries
     * to add it to the list if it doesn't exist already there
     * @param actionEvent
     */
    public void addAlbum(ActionEvent actionEvent) {
        if (!fieldsAreEmpty()){
            MusicAlbum m = new MusicAlbum(this.txtTitle.getText(), this.txtArtist.getText(), Integer.parseInt(this.txtYear.getText()));
            if (!albums.contains(m)){
                albums.add(m);
                FileUtils.saveAlbums(albums);
            }
            this.tblMusic.refresh();
        }
    }

    private boolean fieldsAreEmpty(){
        return (this.txtArtist.getText().equals("") && this.txtTitle.getText().equals("") && this.txtYear.getText().equals(""));
    }

    /**
     * When user clicks a non-empty field of the table
     * the object associated gets selected and
     * printed to textFields
     * @param mouseEvent
     */
    public void tableClick(MouseEvent mouseEvent) {
        MusicAlbum selected = this.tblMusic.getSelectionModel().getSelectedItem();
        if (selected != null){
            this.txtArtist.setText(selected.getAuthor());
            this.txtTitle.setText(selected.getTitle());
            this.txtYear.setText(Integer.toString(selected.getReleaseYear()));
        }
    }

    /**
     * If an object is selected this method reads from the text fields and
     * updates the corresponding object
     * @param actionEvent
     */
    public void editAlbum(ActionEvent actionEvent) {
        MusicAlbum selected = this.tblMusic.getSelectionModel().getSelectedItem();
        if (selected == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("You must select an album first");
            alert.showAndWait();
        }
        else{
            String title = this.txtTitle.getText();
            String author = this.txtArtist.getText();
            try{
                int releaseYear = Integer.parseInt(this.txtYear.getText());
                MusicAlbum aux = new MusicAlbum(title, author, releaseYear);
                if (!albums.contains(aux)){
                    selected.setTitle(aux.getTitle());
                    selected.setAuthor(aux.getAuthor());
                    selected.setReleaseYear(aux.getReleaseYear());
                    this.tblMusic.refresh();
                }
                FileUtils.saveAlbums(albums);
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
     *
     */
    public void deleteAlbum(){
        MusicAlbum selected = this.tblMusic.getSelectionModel().getSelectedItem();
        if (selected == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("You must select an album first");
            alert.showAndWait();
        }
        else{
            albums.remove(selected);
            FileUtils.saveAlbums(albums);
            this.tblMusic.refresh();
        }
    }

    /**
     * This method sets all textFields text property
     * to an empty string
     * and deselects the item from the table
     */
    public void clearSelection(){
        this.txtYear.setText("");
        this.txtTitle.setText("");
        this.txtArtist.setText("");
        this.tblMusic.getSelectionModel().clearSelection();
    }
}
