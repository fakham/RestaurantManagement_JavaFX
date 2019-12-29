package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.boissons.BoissonFactory;
import models.boissons.BoissonType;
import models.plats.Plat;
import services.items.BoissonService;
import util.Item;
import util.Session;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BoissonsController implements Initializable {

    @FXML
    AnchorPane root;
    @FXML
    Button btnCola;
    @FXML
    Button btnFanta;
    @FXML
    Button btnSprite;
    @FXML
    Button btnCoffee;
    @FXML
    Button btnTea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnCoffee.setOnAction(this::boissonAction);
        btnCola.setOnAction(this::boissonAction);
        btnFanta.setOnAction(this::boissonAction);
        btnSprite.setOnAction(this::boissonAction);
        btnTea.setOnAction(this::boissonAction);
    }

    public void boissonAction(ActionEvent actionEvent) {

        Item item = new Item();
        item.setQuantity(1);
        if (actionEvent.getSource().equals(btnCoffee)) {
            item.setPlat(BoissonFactory.getInstance().makeBoisson(BoissonType.COFFEE));
        } else if (actionEvent.getSource().equals(btnTea)) {
            item.setPlat(BoissonFactory.getInstance().makeBoisson(BoissonType.THE));
        } else if (actionEvent.getSource().equals(btnCola)) {
            item.setPlat(BoissonFactory.getInstance().makeBoisson(BoissonType.COLA));
        } else if (actionEvent.getSource().equals(btnFanta)) {
            item.setPlat(BoissonFactory.getInstance().makeBoisson(BoissonType.FANTA));
        } else if (actionEvent.getSource().equals(btnSprite)) {
            item.setPlat(BoissonFactory.getInstance().makeBoisson(BoissonType.SPRITE));
        }

        Plat plat = BoissonService.getInstance().filter("description = '" + item.getPlat().getDescription() + "'").get(0);
        item.setPlat(plat);

        Session.getItems().add(item);

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "The item has been added to your command", ButtonType.OK);
        alert.setTitle("The item has been added to your command!");
        alert.setHeaderText("The item has been added to your command!");
        alert.show();

        menuAction(null);
    }

    public void menuAction(MouseEvent actionEvent) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/Menu.fxml"));
            root.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logOutAction(MouseEvent actionEvent) {
        AnchorPane pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        root.getChildren().setAll(pane);
    }
}
