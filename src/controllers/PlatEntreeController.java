package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import models.plats.Plat;
import models.plats.entree.SaladFactory;
import models.plats.entree.SaladType;
import services.items.PlatEntreeService;
import util.Item;
import util.Session;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlatEntreeController implements Initializable {

    @FXML
    AnchorPane root;
    @FXML
    Button btnChef;
    @FXML
    Button btnGreen;
    @FXML
    Button btnPasta;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void menuAction(ActionEvent actionEvent) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/Menu.fxml"));
            root.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logOutAction(ActionEvent actionEvent) {
        AnchorPane pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        root.getChildren().setAll(pane);
    }

    public void platEntreeAction(ActionEvent actionEvent) {
        Item item = new Item();
        item.setQuantity(1);
        if (actionEvent.getSource().equals(btnChef)) {
            item.setPlat(SaladFactory.getInstance().makeSalad(SaladType.CHEF_SALAD));
        } else if (actionEvent.getSource().equals(btnPasta)) {
            item.setPlat(SaladFactory.getInstance().makeSalad(SaladType.PASTA_SALAD));
        } else if (actionEvent.getSource().equals(btnGreen)) {
            item.setPlat(SaladFactory.getInstance().makeSalad(SaladType.GREEN_SALAD));
        }

        Plat plat = PlatEntreeService.getInstance().filter("description = '" + item.getPlat().getDescription() + "'").get(0);
        item.setPlat(plat);

        Session.getItems().add(item);

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "The item has been added to your command", ButtonType.OK);
        alert.setTitle("The item has been added to your command!");
        alert.setHeaderText("The item has been added to your command!");
        alert.show();

        menuAction(actionEvent);
    }
}
