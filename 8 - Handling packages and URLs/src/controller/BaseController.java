package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import model.Identity;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public abstract class BaseController implements Initializable {

  @FXML
  protected GridPane app;

  protected Identity identity;

  protected String viewURL;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    identity = Identity.getInstance();
    // Na začiatku si načítame URL
    viewURL = System.getProperty("user.dir") + "/src/view/";
  }

  /**
   * Logs out user
   */
  protected void logout() {
    identity.logOut();
    identity = Identity.getInstance();
    switchScene( "login");
  }

  /**
   * Switches between different scenes
   * @param view scene to be viewed
   * */
  public void switchScene(String view) {
    try {
      // Odstránime obsah view, ktorý je momentálne zobrazený
      app.getChildren().clear();

      // Absolútna URL k danému fxml súboru
      URL fxmlURL = Paths.get(viewURL + view + ".fxml").toUri().toURL();

      System.out.println(fxmlURL.toString());
      // Vymeníme za nový obsah
      app.getChildren().add(FXMLLoader.load(fxmlURL));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
