package fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for Welcome window
 * @author Fernando Tortosa Lopez
 */
public class WelcomeController implements Initializable {
    @FXML
    private Button btnMusic;
    @FXML
    private Button btnMovies;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * An event triggered by clicking on the button to go to Music Management window
     * @param actionEvent
     */
    public void btnMusic(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fx/musicManagement.fxml"));
            Parent root = loader.load();
            MusicController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setTitle("Music Management");
            stage.showAndWait();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * An event triggered by clicking on the button to go to Movie Management window
     * @param actionEvent
     */
    public void btnMovies(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fx/movieManagement.fxml"));
            Parent root = loader.load();
            MovieController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setTitle("Movie Management");
            stage.showAndWait();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
